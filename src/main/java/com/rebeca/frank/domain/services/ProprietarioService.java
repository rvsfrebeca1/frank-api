package com.rebeca.frank.domain.services;

import com.rebeca.frank.domain.exception.RecursoNaoEncontradoException;
import com.rebeca.frank.domain.exception.RegraDeConflitoException;
import com.rebeca.frank.domain.model.Proprietario;
import com.rebeca.frank.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class ProprietarioService {
    private final ProprietarioRepository repository;

    public List<Proprietario> listar() {
        return repository.findAll();
    }

    public Proprietario buscar(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Proprietário não encontrado"));
    }

    @Transactional
    public Proprietario adicionar(Proprietario proprietario) {

        if (proprietario.getId() != null) {
            throw new RecursoNaoEncontradoException("Proprietario não encontrado");
        }

        boolean proprietarioComEmailJaExiste = repository.findByEmail(proprietario.getEmail())
                .isPresent();

        if (proprietarioComEmailJaExiste) {
            throw new RegraDeConflitoException("Ja existe um proprietario com esse email cadastrado");
        }

        proprietario.setId(UUID.randomUUID());

        return repository.save(proprietario);
    }

    @Transactional
    public Proprietario atualizar(Proprietario proprietario,
                                  UUID id) {
        boolean proprietarioExiste = repository.existsById(id);
        boolean proprietarioComEsseEmailJaExiste = repository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario))
                .isPresent();


        if (!proprietarioExiste) {
            throw new RecursoNaoEncontradoException("Proprietario não encontrado");
        }

        if (proprietarioComEsseEmailJaExiste) {
            throw new RegraDeConflitoException("Ja existe um proprietario com esse email cadastrado");
        }

        proprietario.setId(id);
        return repository.save(proprietario);
    }

    @Transactional
    public void deletar(UUID id) {
        boolean proprietarioExiste = repository.existsById(id);

        if (!proprietarioExiste) {
            throw new RecursoNaoEncontradoException("Proprietario não encontrado");
        }

        repository.deleteById(id);
    }
}
