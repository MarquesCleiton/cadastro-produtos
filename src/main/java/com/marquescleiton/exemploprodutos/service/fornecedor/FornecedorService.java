package com.marquescleiton.exemploprodutos.service.fornecedor;

import com.marquescleiton.exemploprodutos.domain.entity.Fornecedor;

public interface FornecedorService {
    Boolean isFornecedorCadastradoParaProduto(Long idFornecedor, String codigoBarras);

    public void cadastrarNovoFornecedor(Fornecedor fornecedor);
}
