package com.ecommerce.dadoscompra.service;

import com.ecommerce.dadoscompra.entity.DadosCompraEntity;
import com.ecommerce.dadoscompra.resource.dadosCompra.DadosCompraRequest;

import java.util.Optional;

public interface DadosCompraService {

    Optional<DadosCompraEntity> create(DadosCompraRequest dadosCompraRequest);

    Optional<DadosCompraEntity> updateStatus(String checkoutCode, DadosCompraEntity.Status status);
}
