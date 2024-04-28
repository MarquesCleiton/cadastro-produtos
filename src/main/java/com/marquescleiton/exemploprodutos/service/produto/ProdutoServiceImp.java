package com.marquescleiton.exemploprodutos.service.produto;

import com.marquescleiton.exemploprodutos.domain.entity.Fornecedor;
import com.marquescleiton.exemploprodutos.domain.entity.Produto;
import com.marquescleiton.exemploprodutos.exception.ProdutoNaoCadastradoException;
import com.marquescleiton.exemploprodutos.repository.FornecedorRepository;
import com.marquescleiton.exemploprodutos.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProdutoServiceImp implements ProdutoService{

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    FornecedorRepository fornecedorRepository;

    @Override
    public Produto buscarProdutoPeloCodigoBarras(String codigoBarras) {
        Optional<Produto> produto = produtoRepository.findById(codigoBarras);

        if(Boolean.FALSE.equals(produto.isPresent())){
            throw new ProdutoNaoCadastradoException(null, "Produto com código de barras " + codigoBarras + " não cadastrado");
        }

        List<Fornecedor> fornecedores = fornecedorRepository.findAllFornecedorByCodigoBarrasProduto(produto.get().getCodigoBarras());
        produto.get().setFornecedores(fornecedores);

        return produto.get();
    }

    @Override
    public Produto buscarProdutoMaisRecentePeloIdProduto(Long idProduto) {
        Optional<Produto> produto = produtoRepository.buscaProdutoMaisrecentePeloId(idProduto);

        if(Boolean.FALSE.equals(produto.isPresent())){
            throw new ProdutoNaoCadastradoException(null, "Produto com código " + idProduto + " não cadastrado");
        }

        return produto.get();
    }

    @Override
    @Transactional
    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Boolean isProdutoCadastradoCodigoBarras(String codigoBarras) {
        return produtoRepository.existsById(codigoBarras);
    }
}
