package com.aridev.aritransito.api.controller;

import com.aridev.aritransito.domain.model.Proprietario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProprietarioController {

    @GetMapping("/proprietarios")
    public List<Proprietario> listar(){
        var proprietario1 = new Proprietario();
        proprietario1.setId(1L);
        proprietario1.setNome("Aristezio Junior");
        proprietario1.setEmail("aristezio@email.com");
        proprietario1.setTelefone("85999998877");

        var proprietario2 = new Proprietario();
        proprietario2.setId(2L);
        proprietario2.setNome("Henrique Marinho");
        proprietario2.setEmail("HenriqueCM@email.com");
        proprietario2.setTelefone("85999998866");

        return Arrays.asList(proprietario1, proprietario2);
    }

}
