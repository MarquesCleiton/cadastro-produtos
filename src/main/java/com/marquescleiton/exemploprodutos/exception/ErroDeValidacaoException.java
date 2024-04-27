package com.marquescleiton.exemploprodutos.exception;

import lombok.Getter;

@Getter
public class ErroDeValidacaoException extends RuntimeException{
    private String campo;
    private String mensagem;

    public ErroDeValidacaoException(String campo, String mensagem){
        this.campo = campo;
        this.mensagem = mensagem;
    }
}
