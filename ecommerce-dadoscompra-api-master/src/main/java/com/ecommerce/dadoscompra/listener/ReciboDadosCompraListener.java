package com.ecommerce.dadoscompra.listener;

import com.ecommerce.dadoscompra.entity.DadosCompraEntity;
import com.ecommerce.dadoscompra.service.DadosCompraService;
import com.ecommerce.dadoscompra.streaming.ReciboDadosCompra;
import com.ecommerce.recibo.evento.EventoCriacaoReciboDadosCompra;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReciboDadosCompraListener {

    private final DadosCompraService dadosCompraService;

    @StreamListener(ReciboDadosCompra.INPUT)
    public void handler(EventoCriacaoReciboDadosCompra paymentCreatedEvent) {
        dadosCompraService.updateStatus(paymentCreatedEvent.getCodigoDadosCompra().toString(), DadosCompraEntity.Status.APROVADO);
    }
}
