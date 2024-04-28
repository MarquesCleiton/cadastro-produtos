package com.marquescleiton.exemploprodutos.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.marquescleiton.exemploprodutos.domain.enums.SituacaoFornecedorEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "fornecedor")
@Entity(name = "Fornecedor")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Fornecedor {

//    @EmbeddedId
//    @JsonUnwrapped
//    FornecedorID fornecedorID;

    @Id
    @Column(name = "id_fornecedor")
    private Long idFornecedor;
    @Column(name = "codigo_barras")
    @JsonIgnore
    String codigoBarrasProduto;

//    @ManyToOne
//    @JsonBackReference
//    @JoinColumn(name = "id_produto")
//    private Produto produto;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @OneToMany(
            mappedBy = "situacaoFornecedorId.fornecedor",
            //https://www.baeldung.com/jpa-cascade-types
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SituacaoFornecedor> situacoesFornecedor = new ArrayList<>();

    public void addAtualizacaoSituacao(SituacaoFornecedorEnum situacaoFornecedorEnum){

        SituacaoFornecedor situacaoFornecedor = SituacaoFornecedor.builder()
                .situacaoFornecedorId(new SituacaoFornecedorID(this, situacaoFornecedorEnum.getCodigo()))
                .dataCriacao(LocalDateTime.now())
                .build();

        this.situacoesFornecedor.add(situacaoFornecedor);
    }
}
