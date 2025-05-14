package com.rebeca.frank.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "proprietarios")
public class Proprietario {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    private String nome;

    @EqualsAndHashCode.Include
    private String email;
    private String telefone;
}
