package com.rebeca.frank.api.common;

import com.rebeca.frank.api.converter.AutuacaoConverter;
import com.rebeca.frank.api.converter.ProprietarioConverter;
import com.rebeca.frank.api.converter.VeiculoConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfiguration {
    @Bean
    public VeiculoConverter veiculoConverter(AutuacaoConverter autuacaoConverter) {
        return new VeiculoConverter(autuacaoConverter);
    }

    @Bean
    public ProprietarioConverter proprietarioConverter() {
        return new ProprietarioConverter();
    }

    @Bean
    public AutuacaoConverter autuacaoConverter() {
        return new AutuacaoConverter();
    }
}
