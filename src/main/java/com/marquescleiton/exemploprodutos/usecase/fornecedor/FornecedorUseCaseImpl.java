package com.marquescleiton.exemploprodutos.usecase.fornecedor;

import com.marquescleiton.exemploprodutos.domain.entity.Fornecedor;
import com.marquescleiton.exemploprodutos.domain.entity.Produto;
import com.marquescleiton.exemploprodutos.domain.entity.SituacaoFornecedor;
import com.marquescleiton.exemploprodutos.domain.entity.SituacaoProduto;
import com.marquescleiton.exemploprodutos.domain.enums.SituacaoFornecedorEnum;
import com.marquescleiton.exemploprodutos.domain.enums.SituacaoProdutoEnum;
import com.marquescleiton.exemploprodutos.exception.FornecedorJaCadastradoException;
import com.marquescleiton.exemploprodutos.exception.SituacaoAtualizacaoInvalidaException;
import com.marquescleiton.exemploprodutos.exception.SituacaoInvalidaException;
import com.marquescleiton.exemploprodutos.service.fornecedor.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;

@Component
public class FornecedorUseCaseImpl implements FornecedorUseCase{

    @Autowired
    FornecedorService fornecedorService;
    @Override
    public void validarSeFornecedorJaCadastradoParaproduto(Long idFornecedor, String codigoBarras) {

        Boolean isFornecedorCadastrado = fornecedorService.isFornecedorCadastradoParaProduto(idFornecedor, codigoBarras);

        if(Boolean.TRUE.equals(isFornecedorCadastrado)){
            throw new FornecedorJaCadastradoException(null, "fornecedor: " + idFornecedor + " já cadastrado para o produto " + codigoBarras);
        }
    }

    @Override
    public void validarSituacaoProdutoPermiteFornecedor(Produto produto) {
        SituacaoProdutoEnum situacaoProduto = getUltimaSituacaoProduto(produto);

        if(SituacaoProdutoEnum.ESGOTADO.equals(situacaoProduto)){
            throw new SituacaoAtualizacaoInvalidaException(null, "Produto " + situacaoProduto.getDescricao() + " não permite cadastro ou atualização de fornecedores");
        }
    }

    @Override
    public void validarSeSituacaoFornecedorPermiteAtualizacao(Fornecedor fornecedor, SituacaoFornecedorEnum situacaoAtualizacaoFornecedor) {

        SituacaoFornecedorEnum situacaoAtualFonecedor = getUltimaSituacaoFornecedor(fornecedor);

        if(situacaoAtualFonecedor.equals(situacaoAtualizacaoFornecedor)){
            throw new SituacaoInvalidaException(null, "Fornecedor já se encontra na situação " + situacaoAtualFonecedor.getDescricao());
        }

        if(situacaoAtualFonecedor.equals(SituacaoFornecedorEnum.DESATIVADO)){
            throw new SituacaoInvalidaException(null, "Não é possível alterar situação de um fornecedor " + SituacaoFornecedorEnum.DESATIVADO.getDescricao());
        }
    }

    private SituacaoProdutoEnum getUltimaSituacaoProduto(Produto produto){

        Optional<SituacaoProduto> situacaoProduto = produto.getSituacoesProduto().stream()
                .max(Comparator.comparing(SituacaoProduto::getDataCriacao));

        if(situacaoProduto.isPresent()){
            return SituacaoProdutoEnum.getByCodigo(situacaoProduto.get().getSituacaoId().getCodigoSituacao());
        }

        throw new SituacaoInvalidaException(null, "Produto não tem situação cadastrada");
    }

    private SituacaoFornecedorEnum getUltimaSituacaoFornecedor(Fornecedor fornecedor){

        Optional<SituacaoFornecedor> situacaoFornecedor = fornecedor.getSituacoesFornecedor().stream()
                .max(Comparator.comparing(SituacaoFornecedor::getDataCriacao));

        if(situacaoFornecedor.isPresent()){
            return SituacaoFornecedorEnum.getByCodigo(situacaoFornecedor.get().getSituacaoFornecedorId().getCodigoSituacao());
        }

        throw new SituacaoInvalidaException(null, "Fornecedor não tem situação cadastrada");
    }
}
