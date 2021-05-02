package com.ecommerce.dadoscompra.service;

import com.ecommerce.dadoscompra.entity.DadosCompraEntity;
import com.ecommerce.dadoscompra.entity.DadosCompraProdutoEntity;
import com.ecommerce.dadoscompra.entity.EnderecoEntregaEntity;
import com.ecommerce.dadoscompra.util.UUIDUtil;
import com.ecommerce.dadoscompra.evento.EventoCriacaoDadosCompra;
import com.ecommerce.dadoscompra.repository.DadosCompraRepository;
import com.ecommerce.dadoscompra.resource.dadosCompra.DadosCompraRequest;
import com.ecommerce.dadoscompra.streaming.CriacaoOrigemDadosCompra;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DadosCompraServiceImpl implements DadosCompraService {

    private final DadosCompraRepository dadosCompraRepository;
    private final CriacaoOrigemDadosCompra criacaoOrigemDadosCompra;
    private final UUIDUtil uuidUtil;

    @Override
    public Optional<DadosCompraEntity> create(DadosCompraRequest dadosCompraRequest) {
        log.info("M=create, dadosCompraRequest={}", dadosCompraRequest);
        //constroi objeto
        final DadosCompraEntity dadosCompraEntity = DadosCompraEntity.builder()
                .codigo(uuidUtil.createUUID().toString())
                .status(DadosCompraEntity.Status.CRIADO)
                .salvarEndereco(dadosCompraRequest.getSalvarEndereco())
                .salvarInfo(dadosCompraRequest.getSalvarInfo())
                .enderecoEntrega(EnderecoEntregaEntity.builder()
                                  .endereco(dadosCompraRequest.getEndereco())
                                  .complemento(dadosCompraRequest.getComplemento())
                                  .pais(dadosCompraRequest.getPais())
                                  .estado(dadosCompraRequest.getEstado())
                                  .cep(dadosCompraRequest.getCep())
                                  .build())
                .build();
        dadosCompraEntity.setProdutos(dadosCompraRequest.getProdutos()
                                        .stream()
                                        .map(produto -> DadosCompraProdutoEntity.builder()
                                                .dadosCompra(dadosCompraEntity)
                                                .produto(produto)
                                                .build())
                                        .collect(Collectors.toList()));

        //salva no banco de dados
        final DadosCompraEntity entity = dadosCompraRepository.save(dadosCompraEntity);

        //constroi objeto mensageria
        final EventoCriacaoDadosCompra eventoCriacaoDadosCompra = EventoCriacaoDadosCompra.newBuilder()
                .setCodigoDadosCompra(entity.getCodigo())
                .setStatus(entity.getStatus().name())
                .build();

        //envia objeto para fila
        criacaoOrigemDadosCompra.output().send(MessageBuilder.withPayload(eventoCriacaoDadosCompra).build());

        return Optional.of(entity);
    }

    @Override
    public Optional<DadosCompraEntity> updateStatus(String checkoutCode, DadosCompraEntity.Status status) {
        final DadosCompraEntity dadosCompraEntity = dadosCompraRepository.findByCode(checkoutCode).orElse(DadosCompraEntity.builder().build());
        dadosCompraEntity.setStatus(DadosCompraEntity.Status.APROVADO);
        return Optional.of(dadosCompraRepository.save(dadosCompraEntity));
    }
}
