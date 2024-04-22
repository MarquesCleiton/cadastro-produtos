package com.marquescleiton.exemploprodutos.usecase.impl;

import com.marquescleiton.exemploprodutos.adapter.ProdutoAdapter;
import com.marquescleiton.exemploprodutos.domain.dto.DadosAtualizacaoSituacaoFornecedor;
import com.marquescleiton.exemploprodutos.domain.dto.DadosForncedor;
import com.marquescleiton.exemploprodutos.domain.dto.DadosProduto;
import com.marquescleiton.exemploprodutos.domain.dto.DadosStatus;
import com.marquescleiton.exemploprodutos.domain.entity.*;
import com.marquescleiton.exemploprodutos.domain.enums.SituacaoProdutoEnum;
import com.marquescleiton.exemploprodutos.exception.ProdutoJaCadastradoException;
import com.marquescleiton.exemploprodutos.exception.SituacaoAtualizacaoInvalidaException;
import com.marquescleiton.exemploprodutos.exception.SituacaoInvalidaException;
import com.marquescleiton.exemploprodutos.service.ProdutoService;
import com.marquescleiton.exemploprodutos.usecase.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Component
public class UseCaseImpl implements UseCase {

    ProdutoAdapter produtoAdapter = new ProdutoAdapter();
    @Autowired
    ProdutoService produtoService;

    @Override
    public Produto cadastrarProduto(DadosProduto dadosProduto) {

        validarSeProdutoJaExiste(dadosProduto.id_produto());

        Produto produto = produtoAdapter.dadosProdudoDtoToNovoProdutoEntity(dadosProduto);
        produto = produtoService.cadastrarProduto(produto);

        return produto;
    }

    public Produto buscarProdutoPeloId(Long idProduto){
        return produtoService.buscarProdutoPeloId(idProduto);
    }

    @Override
    public Produto atualizarStatusProduto(DadosStatus dadosStatus) throws SituacaoInvalidaException {

        SituacaoProdutoEnum situacao = SituacaoProdutoEnum.getByCodigo(dadosStatus.codigo_situacao());

        if(situacao == null){
            throw new SituacaoInvalidaException("codigo_situacao", "Código de situação " + dadosStatus.codigo_situacao() + " inválido");
        }

        Produto produto = produtoService.buscarProdutoPeloId(dadosStatus.id_produto());

        if(Boolean.FALSE.equals(isSituacaoValidaAtualizacao(produto, situacao))){
            throw new SituacaoAtualizacaoInvalidaException("codigo_situacao", "Situação atual do produto não permite esta atualização");
        }

        produto.addSituacao(situacao.getCodigo());

        return produtoService.cadastrarProduto(produto);
    }

    @Override
    public Produto cadastrarNovoForcedor(DadosForncedor dadosForncedor) {

        validaFornecedorJaCadastrado(dadosForncedor.id_fornecedor());

        Produto produto = produtoService.buscarProdutoPeloId(dadosForncedor.id_produto());

        produto.addNovoFornecedor(dadosForncedor.id_fornecedor());

        return produtoService.cadastrarProduto(produto);
    }

    @Override
    public Produto buscarFornecedor(Long idFornecedor) {
        Fornecedor fornecedor = produtoService.buscaFornecedorPeloId(idFornecedor);
        return fornecedor.getProduto();
    }

    @Override
    public Produto atualizarStatusFornecedor(DadosAtualizacaoSituacaoFornecedor situacaoFornecedor) {

        SituacaoProdutoEnum situacaoProdutoEnum = SituacaoProdutoEnum.getByCodigo(situacaoFornecedor.codigo_situacao());

        Fornecedor fornecedor = produtoService.buscaFornecedorPeloId(situacaoFornecedor.id_fornecedor());

        validaProdutoAtivo(fornecedor.getProduto());

        fornecedor.getSituacoesFornecedor().add(
                SituacaoFornecedor.builder()
                .situacaoFornecedorId(new SituacaoFornecedorID(fornecedor, situacaoProdutoEnum.getCodigo()))
                .dataCriacao(LocalDateTime.now())
                .build());


        return produtoService.cadastrarProduto(fornecedor.getProduto());
    }

    private void validaProdutoAtivo(Produto produto){
            SituacaoProdutoEnum situacaoProdutoEnum =
                    SituacaoProdutoEnum.getByCodigo(getUltimaSituacaoProduto(produto.getSituacoesProduto()).getSituacaoId().getCodigoSituacao());

        if(Boolean.FALSE.equals(situacaoProdutoEnum.equals(SituacaoProdutoEnum.CADASTRADO))){
            throw new ProdutoJaCadastradoException(null, " Situação do Produto " + produto.getIdProduto() + " não permite atualização");
        }
    }
    private void validaFornecedorJaCadastrado(Long idFornecedor) {
        if(Boolean.TRUE.equals(produtoService.isForncedorCadastrado(idFornecedor))){
            throw new ProdutoJaCadastradoException(null, "Forncedor " + idFornecedor+ " já cadastrado");
        }
    }

    private SituacaoProduto getUltimaSituacaoProduto(List<SituacaoProduto> situacaoProdutos){
        situacaoProdutos.sort(Comparator.comparing(SituacaoProduto::getDataCriacao));
        return situacaoProdutos.get(situacaoProdutos.size() - 1);
    }

    private Boolean isSituacaoValidaAtualizacao(Produto produto, SituacaoProdutoEnum situacaoProdutoEnum){
        SituacaoProduto ultimaSituacao = getUltimaSituacaoProduto(produto.getSituacoesProduto());

        return ultimaSituacao.getSituacaoId().getCodigoSituacao().equals(SituacaoProdutoEnum.CADASTRADO.getCodigo())
                && !situacaoProdutoEnum.equals(SituacaoProdutoEnum.CADASTRADO);
    }

    private void validarSeProdutoJaExiste(Long idProduto){
        if(Boolean.TRUE.equals(produtoService.isProdutoCadastrado(idProduto))){
            throw new ProdutoJaCadastradoException(null, "Produto " + idProduto + " já cadastrado");
        }
    }

    private void validarSeProdutoNaoExiste(Long idProduto){
        if(Boolean.FALSE.equals(produtoService.isProdutoCadastrado(idProduto))){
            throw new ProdutoJaCadastradoException(null, "Produto " + idProduto+ " não cadastrado");
        }
    }

}
