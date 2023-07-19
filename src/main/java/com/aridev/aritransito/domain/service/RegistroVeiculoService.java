package com.aridev.aritransito.domain.service;

import com.aridev.aritransito.domain.exception.NegocioException;
import com.aridev.aritransito.domain.model.Proprietario;
import com.aridev.aritransito.domain.model.StatusVeiculo;
import com.aridev.aritransito.domain.model.Veiculo;
import com.aridev.aritransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final RegistroProprietarioService registroProprietarioService;

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo) {
        if(novoVeiculo.getId() != null){
            throw new NegocioException("Veículo a ser cadastrado não deve possuir um código");
        }

        boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                        .filter(veiculo -> !veiculo.equals(novoVeiculo)).isPresent();

        if(placaEmUso) {
            throw new NegocioException("Já existe um veículo cadastrado com esta placa.");
        }

        Proprietario proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());

        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(LocalDateTime.now());

        return veiculoRepository.save(novoVeiculo);
    }

}
