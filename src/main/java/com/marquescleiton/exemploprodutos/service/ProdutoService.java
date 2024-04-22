package com.marquescleiton.exemploprodutos.service;

import com.marquescleiton.exemploprodutos.domain.entity.Fornecedor;
import com.marquescleiton.exemploprodutos.domain.entity.Produto;

public interface ProdutoService {
    public Produto buscarProdutoPeloId(Long id_produto);

    public Produto cadastrarProduto(Produto produto);

    public Produto cadastrarNovoFornecedor(Produto produto);

    public Boolean isProdutoCadastrado(Long idProduto);

    public Boolean isForncedorCadastrado(Long idFornecedor);

    public Fornecedor buscaFornecedorPeloId(Long idFornecedor);
}
