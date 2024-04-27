package com.marquescleiton.exemploprodutos.usecase.controller;

import com.marquescleiton.exemploprodutos.adapter.FornecedorAdapter;
import com.marquescleiton.exemploprodutos.adapter.ProdutoAdapter;
import com.marquescleiton.exemploprodutos.domain.dto.DadosAtualizacaoSituacaoFornecedor;
import com.marquescleiton.exemploprodutos.domain.dto.DadosForncedor;
import com.marquescleiton.exemploprodutos.domain.dto.DadosProduto;
import com.marquescleiton.exemploprodutos.domain.dto.DadosStatus;
import com.marquescleiton.exemploprodutos.domain.entity.Fornecedor;
import com.marquescleiton.exemploprodutos.domain.entity.Produto;
import com.marquescleiton.exemploprodutos.domain.enums.SituacaoProdutoEnum;
import com.marquescleiton.exemploprodutos.service.fornecedor.FornecedorService;
import com.marquescleiton.exemploprodutos.service.produto.ProdutoService;
import com.marquescleiton.exemploprodutos.usecase.fornecedor.FornecedorUseCase;
import com.marquescleiton.exemploprodutos.usecase.produto.ProdutoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class ControllerUseCaseImpl implements ControllerUseCase{

    private final ProdutoUseCase produtoUseCase;

    private final FornecedorUseCase fornecedorUseCase;


    @Autowired
    ProdutoService produtoService;

    @Autowired
    FornecedorService fornecedorService;

    @Override
    public Produto cadastrarProduto(DadosProduto dadosProduto) {
        produtoUseCase.validarProdutoJaCadastrado(dadosProduto.codigo_barras());
        ProdutoAdapter produtoAdapter = new ProdutoAdapter();
        Produto produto = produtoAdapter.dadosProdudoDtoToNovoProdutoEntity(dadosProduto);

        return produtoService.cadastrarProduto(produto);
    }

    @Override
    public Produto buscarProduto(String codigoBarras) {
        return produtoService.buscarProdutoPeloCodigoBarras(codigoBarras);
    }

    @Override
    public Produto atualizarProduto(DadosStatus dadosStatus) {

        SituacaoProdutoEnum situacaoProduto = SituacaoProdutoEnum.getByCodigo(dadosStatus.codigo_situacao());
        Produto produto = produtoService.buscarProdutoPeloCodigoBarras(dadosStatus.codigo_barras());
        produtoUseCase.validarSituacaoAtualizacaoPermitida(produto, situacaoProduto);
        produto.addSituacao(dadosStatus.codigo_situacao());

        return produtoService.cadastrarProduto(produto);
    }

    @Override
    public Produto cadastrarFornecedor(DadosForncedor dadosForncedor) {

        Produto produto = produtoService.buscarProdutoMaisRecentePeloIdProduto(dadosForncedor.id_produto());
        fornecedorUseCase.validarSeFornecedorJaCadastradoParaproduto(dadosForncedor.id_fornecedor(), produto.getCodigoBarras());
        Fornecedor fornecedor = new FornecedorAdapter().dadosFornecedorDtoToNovoFornecedorEntity(dadosForncedor.id_fornecedor(), produto.getCodigoBarras());
        fornecedorService.cadastrarNovoFornecedor(fornecedor);
        produto.setFornecedores(Collections.singletonList(fornecedor));

        return produto;
    }

    @Override
    public Produto buscarFornecedor(Long idFornecedor) {
        return null;
    }

    @Override
    public Produto atualizarFornecedor(DadosAtualizacaoSituacaoFornecedor situacaoFornecedor) {
        return null;
    }
}
