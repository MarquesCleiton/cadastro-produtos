package com.marquescleiton.exemploprodutos.service.produto;

import com.marquescleiton.exemploprodutos.domain.entity.Produto;

public interface ProdutoService {
    Produto buscarProdutoPeloCodigoBarras(String codigoBarras);

    Produto cadastrarProduto(Produto produto);

    Boolean isProdutoCadastradoCodigoBarras(String codigoBarras);

    Produto buscarProdutoMaisRecentePeloIdProduto(Long idProduto);
}
