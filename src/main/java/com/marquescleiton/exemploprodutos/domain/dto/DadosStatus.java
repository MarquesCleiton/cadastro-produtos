package com.marquescleiton.exemploprodutos.domain.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosStatus(
        @NotBlank
        @Size(min = 21, max = 21, message = "campo deve ter exatamente 21 dígitos")
        String codigo_barras,
        @Digits(integer = 2, message = "deve ter no máximo 2 dígitos", fraction = 0)
        @NotNull
        Integer codigo_situacao
) { }
