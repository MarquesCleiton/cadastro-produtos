package com.marquescleiton.exemploprodutos.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class SituacaoProdutoId implements Serializable {
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "codigo_barras")
    private Produto produto;

    @Column(name = "codigo_situacao")
    private Integer codigoSituacao;
}
