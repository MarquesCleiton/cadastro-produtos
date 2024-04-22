package com.marquescleiton.exemploprodutos.domain.enums;

import com.marquescleiton.exemploprodutos.exception.SituacaoInvalidaException;
import lombok.Getter;

@Getter
public enum SituacaoFornecedorEnum {
    ATIVO(1, "ATIVO"),
    DESATIVADO(2, "DESATIVADO");

    private int codigo;
    private String descricao;
    SituacaoFornecedorEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static SituacaoFornecedorEnum getByCodigo(Integer codigo) {
        for (SituacaoFornecedorEnum status : SituacaoFornecedorEnum.values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new SituacaoInvalidaException("codigo_situacao", "Código de situação " + codigo + " inválido");
    }
}
