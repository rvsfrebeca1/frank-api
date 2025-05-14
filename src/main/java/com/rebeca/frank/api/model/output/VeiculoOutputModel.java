package com.rebeca.frank.api.model.output;

import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record VeiculoOutputModel(
        UUID id,
        ProprietarioResumoOutputModel proprietario,
        String marca,
        String modelo,
        String placa,
        String status,
        List<AutuacaoOutputModel> autuacoes,
        OffsetDateTime dataCadastro,
        OffsetDateTime dataApreensao
) {

    @Builder
    public record ProprietarioResumoOutputModel(UUID id,
                                                String nome) {
    }
}
