package com.marquescleiton.exemploprodutos.usecase;

import com.marquescleiton.exemploprodutos.domain.dto.DadosAtualizacaoSituacaoFornecedor;
import com.marquescleiton.exemploprodutos.domain.dto.DadosForncedor;
import com.marquescleiton.exemploprodutos.domain.dto.DadosProduto;
import com.marquescleiton.exemploprodutos.domain.dto.DadosStatus;
import com.marquescleiton.exemploprodutos.domain.entity.Produto;
import com.marquescleiton.exemploprodutos.exception.SituacaoInvalidaException;

public interface UseCase {
    public Produto cadastrarProduto(DadosProduto dadosProduto);

    Produto buscarProdutoPeloId(Long idProduto);

    Produto atualizarStatusProduto(DadosStatus dadosStatus) throws SituacaoInvalidaException;

    Produto cadastrarNovoForcedor(DadosForncedor dadosForncedor);

    Produto buscarFornecedor(Long idFornecedor);

    Produto atualizarStatusFornecedor(DadosAtualizacaoSituacaoFornecedor situacaoFornecedor);
}
