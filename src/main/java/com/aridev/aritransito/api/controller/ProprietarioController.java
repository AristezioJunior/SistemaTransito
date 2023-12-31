package com.aridev.aritransito.api.controller;

import com.aridev.aritransito.api.assembler.ProprietarioAssembler;
import com.aridev.aritransito.api.model.ProprietarioModel;
import com.aridev.aritransito.api.model.input.ProprietarioInput;
import com.aridev.aritransito.domain.exception.NegocioException;
import com.aridev.aritransito.domain.model.Proprietario;
import com.aridev.aritransito.domain.repository.ProprietarioRepository;
import com.aridev.aritransito.domain.service.RegistroProprietarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {


    private final ProprietarioRepository proprietarioRepository;
    private final RegistroProprietarioService registroProprietarioService;
    private final ProprietarioAssembler proprietarioAssembler;

    @GetMapping
    public List<ProprietarioModel> listar() {
        return proprietarioAssembler.toCollectionModel(proprietarioRepository.findAll());
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<ProprietarioModel> buscar(@PathVariable Long proprietarioId) {
        return proprietarioRepository.findById(proprietarioId)
                .map(proprietarioAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProprietarioModel adicionar(@Valid @RequestBody ProprietarioInput proprietarioInput) {
        Proprietario novoProprietario = proprietarioAssembler.toEntity(proprietarioInput);

        Proprietario proprietarioCadastrado = registroProprietarioService.salvar(novoProprietario);

        return proprietarioAssembler.toModel(proprietarioCadastrado);
    }

    @PutMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long proprietarioId,
                                                 @Valid @RequestBody Proprietario proprietario) {
        if (!proprietarioRepository.existsById(proprietarioId)) {
            return ResponseEntity.notFound().build();
        }

        proprietario.setId(proprietarioId);
        Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);

        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @DeleteMapping("/{proprietarioId}")
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioId) {
        if (!proprietarioRepository.existsById(proprietarioId)) {
            return ResponseEntity.notFound().build();
        }

        registroProprietarioService.excluir(proprietarioId);
        return ResponseEntity.noContent().build();
    }



}
