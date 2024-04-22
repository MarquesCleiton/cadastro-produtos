package com.marquescleiton.exemploprodutos.domain.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoSituacaoFornecedor(

        @Digits(integer = 15, message = "deve ter no máximo 15 dígitos", fraction = 0)
        @NotNull
        Long id_fornecedor,
        @Digits(integer = 2, message = "deve ter no máximo 2 dígitos", fraction = 0)
        @NotNull
        Integer codigo_situacao
) {
}
