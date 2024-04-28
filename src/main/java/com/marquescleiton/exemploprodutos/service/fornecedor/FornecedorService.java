package com.marquescleiton.exemploprodutos.service.fornecedor;

import com.marquescleiton.exemploprodutos.domain.entity.Fornecedor;

import java.util.List;

public interface FornecedorService {
    Boolean isFornecedorCadastradoParaProduto(Long idFornecedor, String codigoBarras);

    void cadastrarFornecedor(Fornecedor fornecedor);

    Fornecedor buscarFornecedorPeloId(Long idFornecedor);

    List<Fornecedor> buscarFornecedoresPeloCodigoBarrasProduto(String codigoBarras);
}
