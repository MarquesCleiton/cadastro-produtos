package com.marquescleiton.exemploprodutos.exception;

public class SituacaoAtualizacaoInvalidaException extends ErroDeValidacaoException{
    public SituacaoAtualizacaoInvalidaException(String campo, String mensagem) {
        super(campo, mensagem);
    }
}
