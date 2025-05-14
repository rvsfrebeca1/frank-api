package com.rebeca.frank.domain.repository;

import com.rebeca.frank.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {
    Optional<Veiculo> findByPlaca(String placa);

}
