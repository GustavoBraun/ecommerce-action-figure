package com.ecommerce.dadoscompra.config;

import com.ecommerce.dadoscompra.streaming.ReciboDadosCompra;
import com.ecommerce.dadoscompra.streaming.CriacaoOrigemDadosCompra;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(value = {
        CriacaoOrigemDadosCompra.class,
        ReciboDadosCompra.class
})
public class StreamingConfig {
}
