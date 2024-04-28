package com.marquescleiton.exemploprodutos.adapter;

import com.marquescleiton.exemploprodutos.domain.dto.DadosProduto;
import com.marquescleiton.exemploprodutos.domain.entity.Produto;
import com.marquescleiton.exemploprodutos.domain.entity.SituacaoProduto;
import com.marquescleiton.exemploprodutos.domain.entity.SituacaoProdutoId;
import com.marquescleiton.exemploprodutos.domain.enums.SituacaoProdutoEnum;

import java.time.LocalDateTime;
import java.util.Collections;

public class ProdutoAdapter {

    public Produto dadosProdudoDtoToNovoProdutoEntity(DadosProduto dadosProduto){
        LocalDateTime datahoraAtual = LocalDateTime.now();
        Produto produto = Produto.builder()
                .codigoBarras(dadosProduto.codigo_barras())
                .idProduto(Long.parseLong(dadosProduto.id_produto()))
                .nomeProduto(dadosProduto.nome_produto())
                .dataCriacao(datahoraAtual)
                .build();

        SituacaoProduto situacaoProduto = SituacaoProduto.builder()
                .situacaoId(new SituacaoProdutoId(produto, SituacaoProdutoEnum.CADASTRADO.getCodigo()))
                .dataCriacao(datahoraAtual)
                .descricaoSituacao(SituacaoProdutoEnum.CADASTRADO.getDescricao())
                .build();

        produto.setSituacoesProduto(Collections.singletonList(situacaoProduto));

        return produto;
    }
}
