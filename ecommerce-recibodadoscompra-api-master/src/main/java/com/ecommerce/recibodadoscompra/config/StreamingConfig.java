package com.ecommerce.recibodadoscompra.config;

import com.ecommerce.recibodadoscompra.streaming.DadosCompraProcessor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(DadosCompraProcessor.class)
public class StreamingConfig {
}
