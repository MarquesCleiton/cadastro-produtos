package com.marquescleiton.exemploprodutos.domain.enums;

import com.marquescleiton.exemploprodutos.exception.SituacaoInvalidaException;
import lombok.Getter;

@Getter
public enum SituacaoProdutoEnum {
    CADASTRADO(1, "CADASTRADO"),
    EM_ESTOQUE(2, "EM ESTOQUE"),
    ESGOTADO(3, "ESGOTADO");

    private final int codigo;
    private final String descricao;
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
        throw new SituacaoInvalidaException(null, "Código de situação " + codigo + " não existe");
    }
}
