package com.ecommerce.dadoscompra.resource.dadosCompra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DadosCompraResponse implements Serializable {

    private String codigo;
}
