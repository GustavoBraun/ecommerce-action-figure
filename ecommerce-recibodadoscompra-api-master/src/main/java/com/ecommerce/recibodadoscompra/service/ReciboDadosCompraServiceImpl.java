package com.ecommerce.recibodadoscompra.service;

import com.ecommerce.dadoscompra.evento.EventoCriacaoDadosCompra;
import com.ecommerce.recibodadoscompra.entity.ReciboDadosCompraEntity;
import com.ecommerce.recibodadoscompra.repository.ReciboDadosCompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReciboDadosCompraServiceImpl implements ReciboDadosCompraService {

    private final ReciboDadosCompraRepository reciboDadosCompraRepository;

    @Override
    public Optional<ReciboDadosCompraEntity> create(EventoCriacaoDadosCompra eventoCriacaoDadosCompra) {
        final ReciboDadosCompraEntity reciboDadosCompraEntity = ReciboDadosCompraEntity.builder()
                .codigoDadosCompra(eventoCriacaoDadosCompra.getCodigoDadosCompra())
                .codigo(UUID.randomUUID().toString())
                .build();
        reciboDadosCompraRepository.save(reciboDadosCompraEntity);
        return Optional.of(reciboDadosCompraEntity);
    }
}
