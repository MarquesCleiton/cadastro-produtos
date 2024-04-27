package com.marquescleiton.exemploprodutos.usecase.produto;

import com.marquescleiton.exemploprodutos.domain.entity.Produto;
import com.marquescleiton.exemploprodutos.domain.enums.SituacaoProdutoEnum;

public interface ProdutoUseCase {
    void validarProdutoJaCadastrado(String codigoBarras);

    void validarSituacaoAtualizacaoPermitida(Produto produto, SituacaoProdutoEnum situacaoProduto);
}
