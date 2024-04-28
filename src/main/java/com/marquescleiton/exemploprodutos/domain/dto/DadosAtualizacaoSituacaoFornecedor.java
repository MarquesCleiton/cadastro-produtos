package com.marquescleiton.exemploprodutos.domain.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoSituacaoFornecedor(

        @NotNull
        @NotBlank
        @Digits(integer = 15, message = "deve ter no máximo 15 dígitos", fraction = 0)
        String id_fornecedor,
        @NotNull
        @NotBlank
        @Digits(integer = 2, message = "deve ter no máximo 2 dígitos", fraction = 0)
        String codigo_situacao
) {
}
