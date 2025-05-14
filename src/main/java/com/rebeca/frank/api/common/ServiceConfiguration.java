package com.rebeca.frank.api.common;

import com.rebeca.frank.domain.repository.ProprietarioRepository;
import com.rebeca.frank.domain.repository.VeiculoRepository;
import com.rebeca.frank.domain.services.AutuacaoService;
import com.rebeca.frank.domain.services.ProprietarioService;
import com.rebeca.frank.domain.services.VeiculoService;
import com.rebeca.frank.domain.utils.Notificador;
import com.rebeca.frank.domain.utils.NotificadorEmail;
import com.rebeca.frank.domain.utils.NotificadorSMS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public Notificador notificadorEmail(){
        return new NotificadorEmail();
    }

    @Bean
    public Notificador notificadorSMS(){
        return new NotificadorSMS();
    }

    @Bean
    public AutuacaoService autuacaoService(VeiculoService veiculoService) {
        return new AutuacaoService(veiculoService);
    }

    @Bean
    public ProprietarioService proprietarioService(ProprietarioRepository proprietarioRepository) {
        return new ProprietarioService(proprietarioRepository);
    }

    @Bean
    public VeiculoService veiculoService(VeiculoRepository veiculoRepository,
                                         ProprietarioService proprietarioService,
                                         @Qualifier("notificadorEmail") Notificador notificador) {
        return new VeiculoService(veiculoRepository, proprietarioService, notificador);
    }
}
