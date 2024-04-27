package com.marquescleiton.exemploprodutos.usecase.produto;

import com.marquescleiton.exemploprodutos.domain.entity.Produto;
import com.marquescleiton.exemploprodutos.domain.entity.SituacaoProduto;
import com.marquescleiton.exemploprodutos.domain.enums.SituacaoProdutoEnum;
import com.marquescleiton.exemploprodutos.exception.ProdutoJaCadastradoException;
import com.marquescleiton.exemploprodutos.exception.SituacaoInvalidaException;
import com.marquescleiton.exemploprodutos.service.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class ProdutoUseCaseImpl implements ProdutoUseCase{

    @Autowired
    ProdutoService produtoService;
    @Override
    public void validarProdutoJaCadastrado(String codigoBarras) {
        if(Boolean.TRUE.equals(produtoService.isProdutoCadastradoCodigoBarras(codigoBarras))){
            throw new ProdutoJaCadastradoException(null, "Produto já cadastrado");
        }
    }

    @Override
    public void validarSituacaoAtualizacaoPermitida(Produto produto, SituacaoProdutoEnum situacaoAtualizacaoProduto) {

        SituacaoProduto situacaoProduto = produto.getSituacoesProduto()
                .stream()
                .max(Comparator.comparing(com.marquescleiton.exemploprodutos.domain.entity.SituacaoProduto::getDataCriacao))
                .orElse(null);

        SituacaoProdutoEnum ultimaSituacao = SituacaoProdutoEnum.getByCodigo(situacaoProduto.getSituacaoId().getCodigoSituacao());

        if(situacaoAtualizacaoProduto.equals(ultimaSituacao)){
            throw new SituacaoInvalidaException(null, "Produto já se encontra na situação: " + ultimaSituacao.getDescricao());
        }

        if(ultimaSituacao.equals(SituacaoProdutoEnum.EM_ESTOQUE) &&
                Boolean.FALSE.equals(situacaoAtualizacaoProduto.equals(SituacaoProdutoEnum.ESGOTADO))){
            throw new SituacaoInvalidaException(null, "Produto " + SituacaoProdutoEnum.EM_ESTOQUE.getDescricao() + " não permite atualizar para " + situacaoAtualizacaoProduto.getDescricao() +". " +
                    "Só é permitido atualizar para " + SituacaoProdutoEnum.ESGOTADO.getDescricao());
        }

        if(ultimaSituacao.equals(SituacaoProdutoEnum.ESGOTADO)){
            throw new SituacaoInvalidaException(null, "Produto " + SituacaoProdutoEnum.ESGOTADO.getDescricao() + " não permite mais atualizações");
        }
    }
}
