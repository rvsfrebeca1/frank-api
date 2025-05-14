package com.rebeca.frank.api.controller;

import com.rebeca.frank.api.converter.ProprietarioConverter;
import com.rebeca.frank.api.model.input.ProprietarioInputModel;
import com.rebeca.frank.api.model.output.ProprietarioOutputModel;
import com.rebeca.frank.domain.model.Proprietario;
import com.rebeca.frank.domain.services.ProprietarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private final ProprietarioService service;
    private final ProprietarioConverter converter;

    @GetMapping
    public List<ProprietarioOutputModel> listar() {
        return converter.toCollectionOutputModel(service.listar());
    }

    @GetMapping("/{id}")
    public ProprietarioOutputModel buscar(@PathVariable UUID id) {
        return converter.toOutputModel(service.buscar(id));
    }

    @PostMapping
    public ProprietarioOutputModel adicionar(@RequestBody @Valid ProprietarioInputModel proprietario) {
        Proprietario inputModel = converter.toDomainModel(proprietario);
        return converter.toOutputModel(service.adicionar(inputModel));
    }

    @PutMapping("/{id}")
    public ProprietarioOutputModel atualizar(@RequestBody @Valid ProprietarioInputModel proprietario,
                                             @PathVariable UUID id) {
        Proprietario inputModel = converter.toDomainModel(proprietario);
        return converter.toOutputModel(service.atualizar(inputModel, id));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }

}
