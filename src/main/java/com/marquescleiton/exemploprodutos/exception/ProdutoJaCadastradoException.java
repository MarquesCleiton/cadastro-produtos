package com.marquescleiton.exemploprodutos.exception;

import lombok.Getter;

@Getter
public class ProdutoJaCadastradoException extends ErroDeValidacaoException{

    public ProdutoJaCadastradoException(String campo, String mensagem) {
        super(campo, mensagem);
    }
}
