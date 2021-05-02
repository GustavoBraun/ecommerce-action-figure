package com.ecommerce.dadoscompra.repository;

import com.ecommerce.dadoscompra.entity.DadosCompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DadosCompraRepository extends JpaRepository<DadosCompraEntity, Long> {

    Optional<DadosCompraEntity> findByCode(String code);
}
