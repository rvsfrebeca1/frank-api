package com.rebeca.frank.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "autuacoes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Autuacao {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne
    private Veiculo veiculo;
    private String descricao;

    @Column(name = "valor_multa")
    private BigDecimal valorDaMulta;

    @Column(name = "data_ocorrencia")
    private OffsetDateTime dataDaOcorrencia;

}
