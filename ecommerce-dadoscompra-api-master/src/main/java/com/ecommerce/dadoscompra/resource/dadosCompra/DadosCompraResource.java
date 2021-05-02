package com.ecommerce.dadoscompra.resource.dadosCompra;

import com.ecommerce.dadoscompra.entity.DadosCompraEntity;
import com.ecommerce.dadoscompra.service.DadosCompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/dadosCompra")
@RequiredArgsConstructor
public class DadosCompraResource {

    private final DadosCompraService dadosCompraService;

    @PostMapping("/")
    public ResponseEntity<DadosCompraResponse> create(@RequestBody DadosCompraRequest dadosCompraRequest) {
        final DadosCompraEntity dadosCompraEntity = dadosCompraService.create(dadosCompraRequest).orElseThrow();

        final DadosCompraResponse dadosCompraResponse = DadosCompraResponse.builder()
                .codigo(dadosCompraEntity.getCodigo())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(dadosCompraResponse);
    }
}
