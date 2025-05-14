package com.rebeca.frank.domain.model;

import com.rebeca.frank.domain.exception.RegraDeNegocioException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "veiculos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Veiculo {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne
    private Proprietario proprietario;

    private String marca;
    private String modelo;
    private String placa;

    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private List<Autuacao> autuacoes = new ArrayList<>();

    public Autuacao adicionarAutuacao(Autuacao autuacao) {
        autuacao.setId(UUID.randomUUID());
        autuacao.setVeiculo(this);
        autuacao.setDataDaOcorrencia(OffsetDateTime.now());
        this.autuacoes.add(autuacao);
        return autuacao;
    }

    public void apreender() {
        if (estaApreendido()) {
            throw new RegraDeNegocioException("O veículo não pode ser apreendido");
        }

        setStatus(StatusVeiculo.APREENDIDO);
        setDataApreensao(OffsetDateTime.now());
    }

    public void removerApreensao() {
        if (naoEstaApreendido()) {
            throw new RegraDeNegocioException("O nao esta apreendido");
        }

        setStatus(StatusVeiculo.REGULAR);
    }

    public boolean estaApreendido() {
        return StatusVeiculo.APREENDIDO.equals(this.getStatus());
    }

    public boolean naoEstaApreendido() {
        return !estaApreendido();
    }

}
