package com.marquescleiton.exemploprodutos.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdutoID implements Serializable {

    @Column(name = "id_produto")
    Long idProduto;

    @Column(name = "codigo_barras")
    String codigoBarras;
}
