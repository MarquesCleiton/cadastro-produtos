package com.marquescleiton.exemploprodutos.exception;

public class FornecedorNaoCadastradoException extends ErroDeValidacaoException{
    public FornecedorNaoCadastradoException(String campo, String mensagem) {
        super(campo, mensagem);
    }
}
