package com.ecommerce.dadoscompra.streaming;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CriacaoOrigemDadosCompra {

    String OUTPUT = "dadoscompra-created-output";

    @Output(OUTPUT)
    MessageChannel output();
}
