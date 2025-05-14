package com.rebeca.frank.api.model.output;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ProprietarioOutputModel(UUID id,
                                      String nome,
                                      String email,
                                      String telefone) {

}
