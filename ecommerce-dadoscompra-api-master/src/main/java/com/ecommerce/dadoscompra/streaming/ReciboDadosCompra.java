package com.ecommerce.dadoscompra.streaming;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ReciboDadosCompra {

    String INPUT = "recibo-dadoscompra-input";

    @Input(INPUT)
    SubscribableChannel input();
}
