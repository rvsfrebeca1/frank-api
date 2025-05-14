package com.rebeca.frank.api.model.input;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AutuacaoInputModel(@NotNull(message = "O campo valorDaMulta é obrigatório") BigDecimal valorDaMulta,
                                 @NotNull(message = "O campo descricao é obrigatório") String descricao) {
}
