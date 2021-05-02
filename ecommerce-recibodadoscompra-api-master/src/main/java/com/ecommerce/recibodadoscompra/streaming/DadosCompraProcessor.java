package com.ecommerce.recibodadoscompra.streaming;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface DadosCompraProcessor {

    String OUTPUT = "recibo-dadoscompra-output";
    String INPUT = "dadoscompra-created-input";

    @Output(OUTPUT)
    MessageChannel output();

    @Input(INPUT)
    SubscribableChannel input();
}
