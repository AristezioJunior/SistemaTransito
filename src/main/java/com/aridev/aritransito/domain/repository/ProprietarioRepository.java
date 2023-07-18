package com.aridev.aritransito.domain.repository;

import com.aridev.aritransito.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

    List<Proprietario> findByNome(String nome);

    Optional<Proprietario> findByEmail(String email);

    List<Proprietario> findByNomeContaining(String nome);

}
