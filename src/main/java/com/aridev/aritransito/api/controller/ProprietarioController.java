package com.aridev.aritransito.api.controller;

import com.aridev.aritransito.domain.model.Proprietario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProprietarioController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/proprietarios")
    public List<Proprietario> listar() {
        return manager.createQuery("from Proprietario", Proprietario.class)
                .getResultList();

    }

}