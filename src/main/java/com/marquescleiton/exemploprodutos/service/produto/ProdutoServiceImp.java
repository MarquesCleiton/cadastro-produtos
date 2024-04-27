package com.marquescleiton.exemploprodutos.service.produto;

import com.marquescleiton.exemploprodutos.domain.entity.Fornecedor;
import com.marquescleiton.exemploprodutos.domain.entity.Produto;
import com.marquescleiton.exemploprodutos.exception.ProdutoJaCadastradoException;
import com.marquescleiton.exemploprodutos.exception.ProdutoNaoCadastradoException;
import com.marquescleiton.exemploprodutos.repository.FornecedorRepository;
import com.marquescleiton.exemploprodutos.repository.ProdutoRepository;
import com.marquescleiton.exemploprodutos.repository.SituacaoFornecedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
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
    public Produto buscaProdutoMaisRecentePeloId(Long idProduto) {
        return null;
    }

    @Override
    public List<Produto> buscarProdutosPeloId(Long id_produto) {

        //return produtoRepository.findAllById(Collections.singleton(id_produto));
        return null;
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
    public Produto cadastrarNovoFornecedor(Fornecedor fornecedor) {
        Produto produto = buscarProdutoPeloCodigoBarras(fornecedor.getCodigoBarrasProduto());

        fornecedor = fornecedorRepository.save(fornecedor);

        produto.getFornecedores().add(fornecedor);

        return produto;
    }

    @Override
    public String buscarCodigoBarrasProdutoMaisRecentePeloId(Long idProduto) {
        Optional<String> codigoBarras = produtoRepository.buscaCodigoBarrasProdutoMaisrecentePeloId(idProduto);

        if(Boolean.FALSE.equals(codigoBarras.isPresent())){
            throw new ProdutoNaoCadastradoException(null, "produto não cadastrado para o ID " +idProduto );
        }
        return codigoBarras.get();
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

    public Boolean isProdutoCadastradoPeloId(Long idProduto){
        return produtoRepository.existsByIdProduto(idProduto);
    }

    @Override
    public Boolean isProdutoCadastradoCodigoBarras(String codigoBarras) {
        return produtoRepository.existsById(codigoBarras);
    }

    @Override
    public Boolean isForncedorCadastrado(Long idFornecedor, String codigoBarrasProduto) {
        return fornecedorRepository.existsByIdFornecedorAndCodigoBarrasProduto(idFornecedor, codigoBarrasProduto);
    }

    @Override
    public Fornecedor buscaFornecedorPeloId(Long idFornecedor) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findByFornecedorId(idFornecedor);

        if(Boolean.FALSE.equals(fornecedor.isPresent())){
            throw new ProdutoJaCadastradoException(null, "Fornecedor " + idFornecedor+ " não cadastrado");
        }

        return fornecedor.get();
    }

    @Override
    public List<Fornecedor> buscaFornecedoresPeloId(Long idFornecedor) {
        return fornecedorRepository.findAllById(Collections.singleton(idFornecedor));
    }
}
