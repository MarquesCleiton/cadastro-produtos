package com.marquescleiton.exemploprodutos.repository;

import com.marquescleiton.exemploprodutos.domain.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
//    public Boolean existsByFornecedorIDIdFornecedor(Long idFornecedor);

//    public Optional<Fornecedor> findByFornecedorIDIdFornecedor(Long idFornecedor);
}
