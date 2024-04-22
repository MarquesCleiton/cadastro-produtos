package com.marquescleiton.exemploprodutos.exception;

public class ErroDeValidacaoException extends RuntimeException{
    String campo;
    String mensagem;

    public ErroDeValidacaoException(String campo, String mensagem){
        this.campo = campo;
        this.mensagem = mensagem;
    }
}
