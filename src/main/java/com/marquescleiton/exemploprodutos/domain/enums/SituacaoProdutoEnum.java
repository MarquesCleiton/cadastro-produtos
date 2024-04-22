package com.marquescleiton.exemploprodutos.domain.enums;

import com.marquescleiton.exemploprodutos.exception.SituacaoInvalidaException;
import lombok.Getter;

@Getter
public enum SituacaoProdutoEnum {
    CADASTRADO(1, "CADASTRADO"),
    ESGOTADO(2, "ESGOTADO");

    private int codigo;
    private String descricao;
    SituacaoProdutoEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static SituacaoProdutoEnum getByCodigo(Integer codigo) {
        for (SituacaoProdutoEnum status : SituacaoProdutoEnum.values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new SituacaoInvalidaException("codigo_situacao", "Código de situação " + codigo + " inválido");
    }
}
