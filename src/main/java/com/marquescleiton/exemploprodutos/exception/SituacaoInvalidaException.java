package com.marquescleiton.exemploprodutos.exception;

import lombok.Getter;

@Getter
public class SituacaoInvalidaException extends ErroDeValidacaoException {

    public SituacaoInvalidaException(String campo, String mensagem) {
        super(campo, mensagem);
    }
}
