package com.marquescleiton.exemploprodutos.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.marquescleiton.exemploprodutos.domain.enums.SituacaoProdutoEnum;
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
    @Column(name = "id_produto")
    Long idProduto;

    @Column(name = "codigo_barras")
    String codigoBarras;

    @Column(name = "nome_produto")
    String nomeProduto;

    @Column(name = "data_criacao")
    LocalDateTime dataCriacao;

    @OneToMany(
            mappedBy = "situacaoId.produto",
            //https://www.baeldung.com/jpa-cascade-types
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<SituacaoProduto> situacoesProduto = new ArrayList<>();

    @OneToMany(
            mappedBy = "produto",
            //https://www.baeldung.com/jpa-cascade-types
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Fornecedor> fornecedores = new ArrayList<>();

    public void addSituacao(Integer situacaoProduto){
        this.situacoesProduto.add(SituacaoProduto.builder()
                .dataCriacao(LocalDateTime.now())
                .situacaoId(new SituacaoProdutoId(this, situacaoProduto))
                .descricaoSituacao(SituacaoProdutoEnum.getByCodigo(situacaoProduto).getDescricao())
                .build());
    }

    public void addNovoFornecedor(Long idFornecedor){
        LocalDateTime dataHoraAtual = LocalDateTime.now();

        Fornecedor fornecedor = Fornecedor.builder()
                .idFornecedor(idFornecedor)
                .produto(this)
                .dataCriacao(dataHoraAtual)
                .situacoesFornecedor(new ArrayList<>())
                .build();

        SituacaoFornecedor situacaoFornecedor = SituacaoFornecedor.builder()
                .situacaoFornecedorId(new SituacaoFornecedorID(fornecedor, 1))
                .dataCriacao(dataHoraAtual)
                .build();

        fornecedor.getSituacoesFornecedor().add(situacaoFornecedor);

        this.fornecedores.add(fornecedor);
    }
}
