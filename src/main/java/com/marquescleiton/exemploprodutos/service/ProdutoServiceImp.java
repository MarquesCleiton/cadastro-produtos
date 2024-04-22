package com.marquescleiton.exemploprodutos.service;

import com.marquescleiton.exemploprodutos.domain.entity.Fornecedor;
import com.marquescleiton.exemploprodutos.domain.entity.Produto;
import com.marquescleiton.exemploprodutos.exception.ProdutoJaCadastradoException;
import com.marquescleiton.exemploprodutos.repository.FornecedorRepository;
import com.marquescleiton.exemploprodutos.repository.ProdutoRepository;
import com.marquescleiton.exemploprodutos.repository.SituacaoFornecedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProdutoServiceImp implements ProdutoService{

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    FornecedorRepository fornecedorRepository;

    @Autowired
    SituacaoFornecedorRepository situacaoFornecedorRepository;

    @Override
    public Produto buscarProdutoPeloId(Long id_produto) {
        return produtoRepository.getReferenceById(id_produto);
    }

    @Override
    @Transactional
    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    @Transactional
    public Produto cadastrarNovoFornecedor(Produto produto) {

//        Produto produto1 = produtoRepository.save(produto);
//
//        SituacaoFornecedor situacaoFornecedor = produto1.getFornecedores().get(0).getSituacaoFornecedor().get(0);
//        situacaoFornecedor = situacaoFornecedorRepository.save(situacaoFornecedor);
//
//        produto1.getFornecedores().get(0).getSituacaoFornecedor().add(situacaoFornecedor);

        return null;
    }

    public Boolean isProdutoCadastrado(Long idProduto){
        return produtoRepository.existsById(idProduto);
    }

    @Override
    public Boolean isForncedorCadastrado(Long idFornecedor) {
        return fornecedorRepository.existsById(idFornecedor);
    }

    @Override
    public Fornecedor buscaFornecedorPeloId(Long idFornecedor) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(idFornecedor);

        if(Boolean.FALSE.equals(fornecedor.isPresent())){
            throw new ProdutoJaCadastradoException(null, "Fornecedor " + idFornecedor+ " não cadastrado");
        }

        return fornecedor.get();
    }
}
