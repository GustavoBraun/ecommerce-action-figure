package com.ecommerce.dadoscompra.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DadosCompraEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String codigo;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column
    private Boolean salvarEndereco;

    @Column
    private Boolean salvarInfo;

    @Column
    @CreatedDate
    private LocalDateTime dataCriacao;

    @Column
    @LastModifiedDate
    private LocalDateTime ultimaDataModificacao;

    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoEntregaEntity enderecoEntrega;

    @OneToMany(mappedBy = "dadosCompra", cascade = CascadeType.ALL)
    private List<DadosCompraProdutoEntity> produtos;

    public enum Status {
        CRIADO,
        APROVADO
    }
}
