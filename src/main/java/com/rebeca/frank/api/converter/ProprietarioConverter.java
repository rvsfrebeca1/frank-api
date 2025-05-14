package com.rebeca.frank.api.converter;

import com.rebeca.frank.api.model.input.ProprietarioInputModel;
import com.rebeca.frank.api.model.output.ProprietarioOutputModel;
import com.rebeca.frank.domain.model.Proprietario;

import java.util.List;

public class ProprietarioConverter {

    public ProprietarioOutputModel toOutputModel(Proprietario proprietario) {
        return ProprietarioOutputModel.builder()
                .id(proprietario.getId())
                .nome(proprietario.getNome())
                .email(proprietario.getEmail())
                .telefone(proprietario.getTelefone())
                .build();
    }

    public List<ProprietarioOutputModel> toCollectionOutputModel(List<Proprietario> proprietarios) {
        return proprietarios.stream().map(this::toOutputModel).toList();
    }

    public Proprietario toDomainModel(ProprietarioInputModel proprietario) {
        return Proprietario.builder()
                .nome(proprietario.nome())
                .email(proprietario.email())
                .telefone(proprietario.telefone())
                .build();
    }
}
