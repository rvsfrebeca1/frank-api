package com.rebeca.frank.domain.repository;

import com.rebeca.frank.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, UUID> {
    Optional<Proprietario> findByEmail(String email);

}
