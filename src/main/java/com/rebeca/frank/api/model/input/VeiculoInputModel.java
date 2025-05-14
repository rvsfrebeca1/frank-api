package com.rebeca.frank.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record VeiculoInputModel(
        @NotNull(message = "O campo proprietarioId não pode ser nulo")
        UUID proprietarioId,
        @NotBlank(message = "O campo marca não pode ser nulo")
        String marca,
        @NotBlank(message = "O campo modelo não pode ser nulo")
        String modelo,
        @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}", message = "A placa deve ter um padrão válido")
        @NotBlank(message = "O campo placa não pode ser nulo")
        String placa
) {
}