package com.marquescleiton.exemploprodutos.repository;

import com.marquescleiton.exemploprodutos.domain.entity.SituacaoProduto;
import com.marquescleiton.exemploprodutos.domain.entity.SituacaoProdutoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SituacaoProdutoRepository extends JpaRepository<SituacaoProduto, SituacaoProdutoId> {

}
