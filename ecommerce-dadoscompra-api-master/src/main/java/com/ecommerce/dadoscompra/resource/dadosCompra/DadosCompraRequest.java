package com.ecommerce.dadoscompra.resource.dadosCompra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosCompraRequest implements Serializable {

    private String primeiroNome;
    private String sobrenome;
    private String email;
    private String endereco;
    private String complemento;
    private String pais;
    private String estado;
    private String cep;
    private Boolean salvarEndereco;
    private Boolean salvarInfo;
    private String metodoPagamento;
    private String nomeCartao;
    private String numeroCartao;
    private String validadeCartao;
    private String cartaoCvv;
    private List<String> produtos;
}

