package com.marquescleiton.exemploprodutos.usecase.controller;

import com.marquescleiton.exemploprodutos.domain.dto.DadosAtualizacaoSituacaoFornecedor;
import com.marquescleiton.exemploprodutos.domain.dto.DadosForncedor;
import com.marquescleiton.exemploprodutos.domain.dto.DadosProduto;
import com.marquescleiton.exemploprodutos.domain.dto.DadosStatus;
import com.marquescleiton.exemploprodutos.domain.entity.Produto;

public interface ControllerUseCase {
    Produto cadastrarProduto(DadosProduto dadosProduto);

    Produto buscarProduto (String codigoBarras);

    Produto atualizarProduto(DadosStatus dadosStatus);

    Produto cadastrarFornecedor(DadosForncedor dadosForncedor);

    Produto buscarFornecedor (Long idFornecedor);

    Produto atualizarFornecedor(DadosAtualizacaoSituacaoFornecedor situacaoFornecedor);
}
