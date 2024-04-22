package com.marquescleiton.exemploprodutos.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.marquescleiton.exemploprodutos.domain.enums.SituacaoFornecedorEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "situacao_fornecedor")
@Entity(name = "SituacaoFornecedor")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SituacaoFornecedor {

    @EmbeddedId
    @JsonUnwrapped
    private SituacaoFornecedorID situacaoFornecedorId;

    @Transient // Não deve ser persistido no banco de dados
    @JsonProperty(value="descricao_situacao")
    private String descricaoSituacao;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    public String getDescricaoSituacao(){
        return SituacaoFornecedorEnum.getByCodigo(this.getSituacaoFornecedorId().getCodigoSituacao()).getDescricao();
    }
}
