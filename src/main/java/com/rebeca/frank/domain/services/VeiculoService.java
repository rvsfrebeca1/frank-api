package com.rebeca.frank.domain.services;

import com.rebeca.frank.domain.exception.BadRequestException;
import com.rebeca.frank.domain.exception.RecursoNaoEncontradoException;
import com.rebeca.frank.domain.exception.RegraDeConflitoException;
import com.rebeca.frank.domain.model.Proprietario;
import com.rebeca.frank.domain.model.StatusVeiculo;
import com.rebeca.frank.domain.model.Veiculo;
import com.rebeca.frank.domain.repository.VeiculoRepository;
import com.rebeca.frank.domain.utils.Notificador;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@AllArgsConstructor
public class VeiculoService {
    private final VeiculoRepository repository;
    private final ProprietarioService proprietarioService;

    private final Notificador notificador;

    public List<Veiculo> listar() {
        return repository.findAll();
    }

    public Veiculo buscar(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Veículo não encontrado"));
    }

    @Transactional
    public Veiculo adicionar(Veiculo veiculo) {

        if (veiculo.getId() != null) {
            throw new BadRequestException("você não pode especificar o id ao criar um veículo");
        }

        boolean placaJaCadastrada = repository.findByPlaca(veiculo.getPlaca())
                .isPresent();

        if (placaJaCadastrada) {
            throw new RegraDeConflitoException("Ja existe um veículo com essa placa cadastrado");
        }

        Proprietario proprietario = proprietarioService.buscar(veiculo.getProprietario().getId());

        veiculo.setId(UUID.randomUUID());
        veiculo.setDataCadastro(OffsetDateTime.now());
        veiculo.setStatus(StatusVeiculo.REGULAR);
        veiculo.setProprietario(proprietario);
        return repository.save(veiculo);
    }

    @Transactional
    public Veiculo atualizar(Veiculo veiculo,
                             UUID id) {
        Optional<Veiculo> veiculoEncontrado = repository.findById(id);
        boolean placaJaCadastrada = repository.findByPlaca(veiculo.getPlaca())
                .filter(p -> !p.equals(veiculo))
                .isPresent();


        if (veiculoEncontrado.isEmpty()) {
            throw new RecursoNaoEncontradoException("Veículo não encontrado");
        }

        if (placaJaCadastrada) {
            throw new RegraDeConflitoException("Ja existe um veículo com essa placa cadastrado");
        }

        veiculo.setId(id);

        return repository.save(veiculo);
    }

    @Transactional
    public void deletar(UUID id) {
        boolean veiculoExiste = repository.existsById(id);

        if (!veiculoExiste) {
            throw new RecursoNaoEncontradoException("Veículo não encontrado");
        }

        repository.deleteById(id);
    }

    @Transactional
    public void apreender(UUID id){
        Veiculo veiculo = this.buscar(id);
        veiculo.apreender();

        String message = criarMensagem(veiculo);
        notificarProprietario(message);
    }

    @Transactional
    public void removerApreensao(UUID id){
        Veiculo veiculo = this.buscar(id);
        veiculo.removerApreensao();
    }

    private String criarMensagem(Veiculo veiculo) {
        return String.format("Atenção %s, seu veículo de placa %s foi apreendido", veiculo.getProprietario().getNome(), veiculo.getPlaca());
    }

    private void notificarProprietario(String mensagem) {
        notificador.notificar( mensagem);
    }
}
