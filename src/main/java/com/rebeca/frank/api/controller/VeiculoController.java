package com.rebeca.frank.api.controller;

import com.rebeca.frank.api.converter.AutuacaoConverter;
import com.rebeca.frank.api.converter.VeiculoConverter;
import com.rebeca.frank.api.model.input.AutuacaoInputModel;
import com.rebeca.frank.api.model.input.VeiculoInputModel;
import com.rebeca.frank.api.model.output.AutuacaoOutputModel;
import com.rebeca.frank.api.model.output.VeiculoOutputModel;
import com.rebeca.frank.domain.model.Autuacao;
import com.rebeca.frank.domain.model.Veiculo;
import com.rebeca.frank.domain.services.AutuacaoService;
import com.rebeca.frank.domain.services.VeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService service;
    private final AutuacaoService autuacaoService;
    private final VeiculoConverter converter;
    private final AutuacaoConverter autuacaoConverter;

    @GetMapping
    public List<VeiculoOutputModel> listar() {
        return converter.toCollectionOutputModel(service.listar());
    }

    @GetMapping("/{id}")
    public VeiculoOutputModel buscar(@PathVariable UUID id) {
        return converter.toOutputModel(service.buscar(id));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public VeiculoOutputModel adicionar(@RequestBody @Valid VeiculoInputModel veiculo) {
        Veiculo inputModel = converter.toDomainModel(veiculo);

        return converter.toOutputModel(service.adicionar(inputModel));
    }

    @PutMapping("/{id}")
    public VeiculoOutputModel atualizar(@RequestBody @Valid VeiculoInputModel veiculo,
                                        @PathVariable UUID id) {
        Veiculo inputModel = converter.toDomainModel(veiculo);

        return converter.toOutputModel(service.atualizar(inputModel, id));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }

    @PostMapping("/{id}/autuacoes")
    public AutuacaoOutputModel registrarAutuacao(@PathVariable UUID id,
                                                 @RequestBody @Valid AutuacaoInputModel autuacao) {
        Autuacao inputModel = autuacaoConverter.toDomainModel(autuacao);
        return autuacaoConverter.toOutputModel(autuacaoService.registrar(id, inputModel));
    }

    @GetMapping("/{id}/autuacoes")
    public List<AutuacaoOutputModel> listarAutuacoes(@PathVariable UUID id) {
        return autuacaoConverter.toCollectionOutputModel(service.buscar(id).getAutuacoes());
    }

    @PutMapping("/{id}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apreender(@PathVariable UUID id){
        service.apreender(id);
    }

    @DeleteMapping("/{id}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerApreensao(@PathVariable UUID id){
        service.removerApreensao(id);
    }
}
