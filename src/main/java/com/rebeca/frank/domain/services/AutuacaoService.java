package com.rebeca.frank.domain.services;

import com.rebeca.frank.domain.model.Autuacao;
import com.rebeca.frank.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class AutuacaoService {
    private final VeiculoService veiculoService;

    @Transactional
    public Autuacao registrar(UUID veiculoId, Autuacao autuacao){
        Veiculo veiculo = veiculoService.buscar(veiculoId);
        return veiculo.adicionarAutuacao(autuacao);
    }
}
