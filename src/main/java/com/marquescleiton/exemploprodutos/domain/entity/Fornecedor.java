package com.marquescleiton.exemploprodutos.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "fornecedor")
@Entity(name = "Fornecedor")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Fornecedor {

//    @EmbeddedId
//    @JsonUnwrapped
//    FornecedorID fornecedorID;

    @Id
    @Column(name = "id_fornecedor")
    private Long idFornecedor;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @OneToMany(
            mappedBy = "situacaoFornecedorId.fornecedor",
            //https://www.baeldung.com/jpa-cascade-types
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SituacaoFornecedor> situacoesFornecedor = new ArrayList<>();
}
