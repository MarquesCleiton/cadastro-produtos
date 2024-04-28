package com.marquescleiton.exemploprodutos.domain.dto;

import jakarta.validation.constraints.*;

public record DadosProduto(

        @NotBlank(message = "Não pode ser vazio")
        @NotNull(message = "não pode ser nulo")
        @Digits(integer = 15, message = "Deve ser numérico com máximo 15 dígitos", fraction = 0)
        @Min(1)
        String id_produto,

        @NotNull
        @NotBlank
        @Digits(integer = 21, message = "deve ter no máximo 15 dígitos", fraction = 0)
        @Size(min = 21, max = 21, message = "campo deve ter exatamente 21 dígitos")
        String codigo_barras,

        @NotBlank
        @NotNull
        String nome_produto
) { }
