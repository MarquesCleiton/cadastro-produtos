package com.marquescleiton.exemploprodutos.exception;

public class ProdutoNaoCadastradoException extends ErroDeValidacaoException{

    public ProdutoNaoCadastradoException(String campo, String mensagem) {
        super(campo, mensagem);
    }
}
