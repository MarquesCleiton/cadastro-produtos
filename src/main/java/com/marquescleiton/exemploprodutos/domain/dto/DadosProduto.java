package com.marquescleiton.exemploprodutos.domain.dto;

import jakarta.validation.constraints.*;

public record DadosProduto(
        @Digits(integer = 15, message = "deve ter no máximo 15 dígitos", fraction = 0)
        @NotNull
        Long id_produto,

        @NotBlank
        @Size(min = 21, max = 21, message = "campo deve ter exatamente 21 dígitos")
        String codigo_barras,

        @NotBlank
        @NotNull
        String nome_produto
) { }
