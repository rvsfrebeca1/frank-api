package com.rebeca.frank.api.converter;

import com.rebeca.frank.api.model.input.VeiculoInputModel;
import com.rebeca.frank.api.model.output.AutuacaoOutputModel;
import com.rebeca.frank.api.model.output.VeiculoOutputModel;
import com.rebeca.frank.domain.model.Proprietario;
import com.rebeca.frank.domain.model.Veiculo;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class VeiculoConverter {

    private final AutuacaoConverter autuacaoConverter;

    public VeiculoOutputModel toOutputModel(Veiculo veiculo) {
        return VeiculoOutputModel.builder()
                .id(veiculo.getId())
                .proprietario(VeiculoOutputModel.ProprietarioResumoOutputModel.builder()
                        .id(veiculo.getProprietario().getId())
                        .nome(veiculo.getProprietario().getNome())
                        .build())
                .marca(veiculo.getMarca())
                .modelo(veiculo.getModelo())
                .placa(veiculo.getPlaca())
                .status(veiculo.getStatus().name())
                .autuacoes(autuacaoConverter.toCollectionOutputModel(veiculo.getAutuacoes()))
                .dataApreensao(veiculo.getDataApreensao())
                .dataCadastro(veiculo.getDataCadastro())
                .build();
    }

    public List<VeiculoOutputModel> toCollectionOutputModel(List<Veiculo> veiculos) {
        return veiculos.stream().map(this::toOutputModel).toList();
    }

    public Veiculo toDomainModel(VeiculoInputModel veiculo) {
        return Veiculo.builder()
                .proprietario(Proprietario.builder()
                        .id(veiculo.proprietarioId())
                        .build())
                .modelo(veiculo.modelo())
                .placa(veiculo.placa())
                .marca(veiculo.marca())
                .build();
    }
}
