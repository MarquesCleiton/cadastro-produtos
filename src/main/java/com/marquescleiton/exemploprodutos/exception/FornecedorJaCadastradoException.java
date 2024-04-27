package com.marquescleiton.exemploprodutos.exception;

public class FornecedorJaCadastradoException extends ErroDeValidacaoException{
    public FornecedorJaCadastradoException(String campo, String mensagem) {
        super(campo, mensagem);
    }
}
