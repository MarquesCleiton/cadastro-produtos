package com.marquescleiton.exemploprodutos.usecase.controller;

import com.marquescleiton.exemploprodutos.adapter.FornecedorAdapter;
import com.marquescleiton.exemploprodutos.adapter.ProdutoAdapter;
import com.marquescleiton.exemploprodutos.domain.dto.DadosAtualizacaoSituacaoFornecedor;
import com.marquescleiton.exemploprodutos.domain.dto.DadosForncedor;
import com.marquescleiton.exemploprodutos.domain.dto.DadosProduto;
import com.marquescleiton.exemploprodutos.domain.dto.DadosStatus;
import com.marquescleiton.exemploprodutos.domain.entity.Fornecedor;
import com.marquescleiton.exemploprodutos.domain.entity.Produto;
import com.marquescleiton.exemploprodutos.domain.enums.SituacaoFornecedorEnum;
import com.marquescleiton.exemploprodutos.domain.enums.SituacaoProdutoEnum;
import com.marquescleiton.exemploprodutos.service.fornecedor.FornecedorService;
import com.marquescleiton.exemploprodutos.service.produto.ProdutoService;
import com.marquescleiton.exemploprodutos.usecase.fornecedor.FornecedorUseCase;
import com.marquescleiton.exemploprodutos.usecase.produto.ProdutoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        Produto produto = new ProdutoAdapter().dadosProdudoDtoToNovoProdutoEntity(dadosProduto);

        return produtoService.cadastrarProduto(produto);
    }

    @Override
    public Produto buscarProduto(String codigoBarras) {
        return produtoService.buscarProdutoPeloCodigoBarras(codigoBarras);
    }

    @Override
    public Produto atualizarProduto(DadosStatus dadosStatus) {
        Integer codigoSituacaoAtualizacaoProduto = Integer.parseInt(dadosStatus.codigo_situacao());

        SituacaoProdutoEnum situacaoProduto = SituacaoProdutoEnum.getByCodigo(codigoSituacaoAtualizacaoProduto);
        Produto produto = produtoService.buscarProdutoPeloCodigoBarras(dadosStatus.codigo_barras());
        produtoUseCase.validarSituacaoAtualizacaoPermitida(produto, situacaoProduto);
        produto.addSituacao(codigoSituacaoAtualizacaoProduto);

        return produtoService.cadastrarProduto(produto);
    }

    @Override
    public Produto cadastrarFornecedor(DadosForncedor dadosForncedor) {

        Produto produto = produtoService.buscarProdutoMaisRecentePeloIdProduto(dadosForncedor.id_produto());
        fornecedorUseCase.validarSituacaoProdutoPermiteFornecedor(produto);
        fornecedorUseCase.validarSeFornecedorJaCadastradoParaproduto(dadosForncedor.id_fornecedor(), produto.getCodigoBarras());
        Fornecedor fornecedor = new FornecedorAdapter().dadosFornecedorDtoToNovoFornecedorEntity(dadosForncedor.id_fornecedor(), produto.getCodigoBarras());
        fornecedorService.cadastrarFornecedor(fornecedor);

        return produtoService.buscarProdutoPeloCodigoBarras(produto.getCodigoBarras());
    }

    @Override
    public Produto buscarFornecedor(Long idFornecedor) {

        Fornecedor fornecedor = fornecedorService.buscarFornecedorPeloId(idFornecedor);
        Produto produto = produtoService.buscarProdutoPeloCodigoBarras(fornecedor.getCodigoBarrasProduto());
        List<Fornecedor> fornecedores = fornecedorService.buscarFornecedoresPeloCodigoBarrasProduto(produto.getCodigoBarras());
        produto.setFornecedores(fornecedores);

        return produto;
    }

    @Override
    public Produto atualizarFornecedor(DadosAtualizacaoSituacaoFornecedor situacaoFornecedor) {

        Fornecedor fornecedor = fornecedorService.buscarFornecedorPeloId(Long.parseLong(situacaoFornecedor.id_fornecedor()));
        SituacaoFornecedorEnum situacaoAtualizacaoFornecedorEnum = SituacaoFornecedorEnum.getByCodigo(Integer.parseInt(situacaoFornecedor.codigo_situacao()));
        fornecedorUseCase.validarSeSituacaoFornecedorPermiteAtualizacao(fornecedor, situacaoAtualizacaoFornecedorEnum);
        Produto produto = produtoService.buscarProdutoPeloCodigoBarras(fornecedor.getCodigoBarrasProduto());
        fornecedorUseCase.validarSituacaoProdutoPermiteFornecedor(produto);
        fornecedor.addAtualizacaoSituacao(situacaoAtualizacaoFornecedorEnum);
        fornecedorService.cadastrarFornecedor(fornecedor);

        return produtoService.buscarProdutoPeloCodigoBarras(fornecedor.getCodigoBarrasProduto());
    }
}
