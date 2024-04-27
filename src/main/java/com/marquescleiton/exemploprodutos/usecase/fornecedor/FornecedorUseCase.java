package com.marquescleiton.exemploprodutos.usecase.fornecedor;

public interface FornecedorUseCase {
    void validarSeFornecedorJaCadastradoParaproduto(Long idProduto, String codigoBarras);
}
