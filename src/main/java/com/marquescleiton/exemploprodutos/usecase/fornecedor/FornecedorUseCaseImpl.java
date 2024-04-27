package com.marquescleiton.exemploprodutos.usecase.fornecedor;

import com.marquescleiton.exemploprodutos.exception.FornecedorJaCadastradoException;
import com.marquescleiton.exemploprodutos.service.fornecedor.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FornecedorUseCaseImpl implements FornecedorUseCase{

    @Autowired
    FornecedorService fornecedorService;
    @Override
    public void validarSeFornecedorJaCadastradoParaproduto(Long idFornecedor, String codigoBarras) {

        Boolean isFornecedorCadastrado = fornecedorService.isFornecedorCadastradoParaProduto(idFornecedor, codigoBarras);

        if(isFornecedorCadastrado){
            throw new FornecedorJaCadastradoException(null, "fornecedor: " + idFornecedor + " já cadastrado para o produto " + codigoBarras);
        }
    }
}
