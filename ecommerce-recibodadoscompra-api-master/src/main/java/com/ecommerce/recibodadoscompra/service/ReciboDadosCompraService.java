package com.ecommerce.recibodadoscompra.service;

import com.ecommerce.dadoscompra.evento.EventoCriacaoDadosCompra;
import com.ecommerce.recibodadoscompra.entity.ReciboDadosCompraEntity;

import java.util.Optional;

public interface ReciboDadosCompraService {

    Optional<ReciboDadosCompraEntity> create(EventoCriacaoDadosCompra eventoCriacaoDadosCompra);
}
