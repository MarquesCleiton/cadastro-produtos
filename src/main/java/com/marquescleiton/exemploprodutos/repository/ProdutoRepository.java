package com.marquescleiton.exemploprodutos.repository;

import com.marquescleiton.exemploprodutos.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
