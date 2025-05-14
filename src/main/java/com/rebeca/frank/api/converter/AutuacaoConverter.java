package com.rebeca.frank.api.converter;

import com.rebeca.frank.api.model.input.AutuacaoInputModel;
import com.rebeca.frank.api.model.input.VeiculoInputModel;
import com.rebeca.frank.api.model.output.AutuacaoOutputModel;
import com.rebeca.frank.api.model.output.VeiculoOutputModel;
import com.rebeca.frank.domain.model.Autuacao;
import com.rebeca.frank.domain.model.Proprietario;
import com.rebeca.frank.domain.model.Veiculo;

import java.util.Collections;
import java.util.List;

public class AutuacaoConverter {

    public AutuacaoOutputModel toOutputModel(Autuacao autuacao) {
        return AutuacaoOutputModel.builder()
                .id(autuacao.getId())
                .descricao(autuacao.getDescricao())
                .valorDaMulta(autuacao.getValorDaMulta())
                .dataDaOcorrencia(autuacao.getDataDaOcorrencia())
                .build();
    }

    public List<AutuacaoOutputModel> toCollectionOutputModel(List<Autuacao> autuacaos) {
        if(autuacaos.isEmpty()){
            return Collections.emptyList();
        }
        return autuacaos.stream().map(this::toOutputModel).toList();
    }

    public Autuacao toDomainModel(AutuacaoInputModel autuacao) {
        return Autuacao.builder()
                .descricao(autuacao.descricao())
                .valorDaMulta(autuacao.valorDaMulta())
                .build();
    }
}
