package com.rebeca.frank.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ProprietarioInputModel(@NotBlank(message = "O campo nome não pode ser nulo")
                                     String nome,
                                     @NotBlank(message = "O campo email não pode ser nulo")
                                     String email,
                                     @NotBlank(message = "O campo telefone não pode ser nulo")
                                     String telefone) {
}
