package com.marquescleiton.exemploprodutos.repository;

import com.marquescleiton.exemploprodutos.domain.entity.SituacaoFornecedor;
import com.marquescleiton.exemploprodutos.domain.entity.SituacaoFornecedorID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SituacaoFornecedorRepository extends JpaRepository<SituacaoFornecedor, SituacaoFornecedorID> {
}
