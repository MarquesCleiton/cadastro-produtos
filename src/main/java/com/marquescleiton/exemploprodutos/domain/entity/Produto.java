package com.marquescleiton.exemploprodutos.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "produto")
@Entity(name = "Produto")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Produto {

    @Id
    @Column(name = "codigo_barras")
    String codigoBarras;

    @Column(name = "id_produto")
    Long idProduto;

    @Column(name = "nome_produto")
    String nomeProduto;

    @Column(name = "data_criacao")
    LocalDateTime dataCriacao;

    @OneToMany(
            mappedBy = "situacaoId.produto",
            //https://www.baeldung.com/jpa-cascade-types
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<SituacaoProduto> situacoesProduto = new ArrayList<>();

//    @OneToMany(
//            mappedBy = "produto",
//            //https://www.baeldung.com/jpa-cascade-types
//            cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER)
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    private List<Fornecedor> fornecedores = new ArrayList<>();

    @Transient
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("fornecedores")
    private List<Fornecedor> fornecedores = new ArrayList<>();

    public void addSituacao(Integer situacaoProduto){
        this.situacoesProduto.add(SituacaoProduto.builder()
                .dataCriacao(LocalDateTime.now())
                .situacaoId(new SituacaoProdutoId(this, situacaoProduto))
                .build());
    }
}
