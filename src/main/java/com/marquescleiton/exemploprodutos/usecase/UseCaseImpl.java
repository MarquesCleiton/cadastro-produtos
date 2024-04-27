package com.marquescleiton.exemploprodutos.usecase;

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
import com.marquescleiton.exemploprodutos.service.produto.ProdutoService;
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

        validarSeProdutoJaExistePeloCodigoBarras(dadosProduto.codigo_barras());

        Produto produto = produtoAdapter.dadosProdudoDtoToNovoProdutoEntity(dadosProduto);

        return produtoService.cadastrarProduto(produto);
    }


    public Produto buscarProdutoPeloCodigoBarras(String codigoBarras){
        return produtoService.buscarProdutoPeloCodigoBarras(codigoBarras);
    }

    @Override
    public Produto atualizarStatusProduto(DadosStatus dadosStatus) throws SituacaoInvalidaException {

        SituacaoProdutoEnum situacao = ValidaSeSituacaoProdutoExiste(dadosStatus.codigo_situacao());

        Produto produto = produtoService.buscarProdutoPeloCodigoBarras(dadosStatus.codigo_barras());

        if(Boolean.FALSE.equals(isSituacaoValidaAtualizacao(produto, situacao))){
            throw new SituacaoAtualizacaoInvalidaException("codigo_situacao", "Situação atual do produto não permite esta atualização");
        }

        produto.addSituacao(situacao.getCodigo());

        return produtoService.cadastrarProduto(produto);
    }

    private SituacaoProdutoEnum ValidaSeSituacaoProdutoExiste(Integer codigoSituacao) {
        SituacaoProdutoEnum situacao = SituacaoProdutoEnum.getByCodigo(codigoSituacao);

        if(situacao == null){
            throw new SituacaoInvalidaException("codigo_situacao", "Código de situação " + codigoSituacao + " inválido");
        }
        return situacao;
    }

    @Override
    public Produto cadastrarNovoForcedor(DadosForncedor dadosForncedor) {

        //validaFornecedorJaCadastrado(dadosForncedor.id_fornecedor(), dadosForncedor.codigo_barras());
        //validarSeProdutoJaExiste();


        Fornecedor fornecedor = Fornecedor.builder()
                //.idProduto(dadosForncedor.id_produto())
                .idFornecedor(dadosForncedor.id_fornecedor())
                .dataCriacao(LocalDateTime.now())
                .build();

        return produtoService.cadastrarNovoFornecedor(fornecedor);
    }

    @Override
    public Produto buscarFornecedor(Long idFornecedor) {

        Fornecedor fornecedor = produtoService.buscaFornecedorPeloId(idFornecedor);
        Produto produto = null; //produtoService.buscarProdutoPeloCodigoBarras(fornecedor.getIdProduto());

        return produto;
    }

    @Override
    public Produto atualizarStatusFornecedor(DadosAtualizacaoSituacaoFornecedor situacaoFornecedor) {

        SituacaoProdutoEnum situacaoProdutoEnum = SituacaoProdutoEnum.getByCodigo(situacaoFornecedor.codigo_situacao());

        Fornecedor fornecedor = produtoService.buscaFornecedorPeloId(situacaoFornecedor.id_fornecedor());

        //validaProdutoAtivo(fornecedor.getProduto());

        fornecedor.getSituacoesFornecedor().add(
                SituacaoFornecedor.builder()
                .situacaoFornecedorId(new SituacaoFornecedorID(fornecedor, situacaoProdutoEnum.getCodigo()))
                .dataCriacao(LocalDateTime.now())
                .build());


        //return produtoService.cadastrarProduto(fornecedor.getProduto());
        return null;
    }

    private void validaProdutoAtivo(Produto produto){
            SituacaoProdutoEnum situacaoProdutoEnum =
                    SituacaoProdutoEnum.getByCodigo(getUltimaSituacaoProduto(produto.getSituacoesProduto()).getSituacaoId().getCodigoSituacao());

        if(Boolean.FALSE.equals(situacaoProdutoEnum.equals(SituacaoProdutoEnum.EM_ESTOQUE))){
            throw new ProdutoJaCadastradoException(null, " Situação do Produto " + produto.getIdProduto() + " não permite atualização");
        }
    }
    private void validaFornecedorJaCadastrado(Long idFornecedor, String codigoBarrasProduto) {

        if(Boolean.TRUE.equals(produtoService.isForncedorCadastrado(idFornecedor, codigoBarrasProduto))){
            throw new ProdutoJaCadastradoException(null, "Forncedor " + idFornecedor+ " já cadastrado para o produto com o numéro de barras" + codigoBarrasProduto);
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

    private void validarSeProdutoJaExistePeloCodigoBarras(String codigoBarras){
        if(Boolean.TRUE.equals(produtoService.isProdutoCadastradoCodigoBarras(codigoBarras))){
            throw new ProdutoJaCadastradoException(null, "Produto com o código de barras" + codigoBarras + " já cadastrado");
        }
    }

    private void validarSeProdutoJaExistePelo(Long idProduto){
        if(Boolean.TRUE.equals(produtoService.isProdutoCadastradoPeloId(idProduto))){
            throw new ProdutoJaCadastradoException(null, "Produto " + idProduto + " já cadastrado");
        }
    }
    private void validarSeProdutoNaoExiste(Long idProduto){
        if(Boolean.FALSE.equals(produtoService.isProdutoCadastradoPeloId(idProduto))){
            throw new ProdutoJaCadastradoException(null, "Produto " + idProduto+ " não cadastrado");
        }
    }

}
