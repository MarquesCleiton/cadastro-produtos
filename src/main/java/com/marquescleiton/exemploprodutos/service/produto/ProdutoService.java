package com.marquescleiton.exemploprodutos.service.produto;

import com.marquescleiton.exemploprodutos.domain.entity.Fornecedor;
import com.marquescleiton.exemploprodutos.domain.entity.Produto;

import java.util.List;

public interface ProdutoService {
    public Produto buscarProdutoPeloCodigoBarras(String codigoBarras);

    public Produto buscaProdutoMaisRecentePeloId(Long idProduto);
    public Produto cadastrarProduto(Produto produto);

    public Produto cadastrarNovoFornecedor(Produto produto);

    public Boolean isProdutoCadastradoPeloId(Long idProduto);

    public Boolean isProdutoCadastradoCodigoBarras(String codigoBarras);

    public Boolean isForncedorCadastrado(Long idFornecedor, String codigoBarrasProduto);

    public Fornecedor buscaFornecedorPeloId(Long idFornecedor);

    public List<Fornecedor> buscaFornecedoresPeloId(Long idFornecedor);

    List<Produto> buscarProdutosPeloId(Long id_produto);

    public Produto buscarProdutoMaisRecentePeloIdProduto(Long idProduto);

    public Produto cadastrarNovoFornecedor(Fornecedor fornecedor);

    String buscarCodigoBarrasProdutoMaisRecentePeloId(Long idProduto);
}
