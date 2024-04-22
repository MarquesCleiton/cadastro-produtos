package com.marquescleiton.exemploprodutos.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.marquescleiton.exemploprodutos.domain.enums.SituacaoProdutoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "situacao_produto")
@Entity(name = "SituacaoProduto")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SituacaoProduto {

    @EmbeddedId
    @JsonUnwrapped
    private SituacaoProdutoId situacaoId;

    @Transient // Não deve ser persistido no banco de dados
    @JsonProperty(value="descricao_situacao")
    private String descricaoSituacao;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    public String getDescricaoSituacao(){
        return SituacaoProdutoEnum.getByCodigo(this.getSituacaoId().getCodigoSituacao()).getDescricao();
    }

}
