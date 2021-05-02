package com.ecommerce.recibodadoscompra.listener;

import com.ecommerce.recibodadoscompra.entity.ReciboDadosCompraEntity;
import com.ecommerce.recibodadoscompra.service.ReciboDadosCompraService;
import com.ecommerce.dadoscompra.evento.EventoCriacaoDadosCompra;
import com.ecommerce.recibo.evento.EventoCriacaoReciboDadosCompra;
import com.ecommerce.recibodadoscompra.streaming.DadosCompraProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CriacaoDadosCompraListener {

    private final DadosCompraProcessor dadosCompraProcessor;

    private final ReciboDadosCompraService reciboDadosCompraService;

    @StreamListener(DadosCompraProcessor.INPUT)
    public void handler(EventoCriacaoDadosCompra eventoCriacaoDadosCompra) {
        log.info("eventoCriacaoDadosCompra={}", eventoCriacaoDadosCompra);
        final ReciboDadosCompraEntity reciboDadosCompraEntity = reciboDadosCompraService.create(eventoCriacaoDadosCompra).orElseThrow();
        final EventoCriacaoReciboDadosCompra eventoCriacaoReciboDadosCompra = EventoCriacaoReciboDadosCompra.newBuilder()
                .setCodigoDadosCompra(reciboDadosCompraEntity.getCodigoDadosCompra())
                .setCodigoPagamento(reciboDadosCompraEntity.getCodigo())
                .build();
        dadosCompraProcessor.output().send(MessageBuilder.withPayload(eventoCriacaoReciboDadosCompra).build());
    }
}
