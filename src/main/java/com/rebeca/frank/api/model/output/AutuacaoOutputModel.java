package com.rebeca.frank.api.model.output;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
public record AutuacaoOutputModel(UUID id,
                                  BigDecimal valorDaMulta,
                                  OffsetDateTime dataDaOcorrencia,
                                  String descricao) {
}
