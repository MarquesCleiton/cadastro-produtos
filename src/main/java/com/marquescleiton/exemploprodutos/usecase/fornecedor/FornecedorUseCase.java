package com.marquescleiton.exemploprodutos.usecase.fornecedor;

import com.marquescleiton.exemploprodutos.domain.entity.Fornecedor;
import com.marquescleiton.exemploprodutos.domain.entity.Produto;
import com.marquescleiton.exemploprodutos.domain.enums.SituacaoFornecedorEnum;

public interface FornecedorUseCase {
    void validarSeFornecedorJaCadastradoParaproduto(Long idProduto, String codigoBarras);

    void validarSituacaoProdutoPermiteFornecedor(Produto produto);

    void validarSeSituacaoFornecedorPermiteAtualizacao(Fornecedor fornecedor, SituacaoFornecedorEnum situacaoAtualizacaoFornecedor);
}
