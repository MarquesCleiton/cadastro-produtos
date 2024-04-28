package com.marquescleiton.exemploprodutos.repository;

import com.marquescleiton.exemploprodutos.domain.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    public Boolean existsByIdFornecedorAndCodigoBarrasProduto(Long idFornecedor, String codigoBarrasProduto);
    @Query(value = "select * from fornecedor f " +
            "JOIN produto p ON f.codigo_barras = p.codigo_barras " +
            "where f.id_fornecedor = :idFornecedor" +
            "Order by p.data_criacao LIMIT 1", nativeQuery = true)
    public Optional<Fornecedor> findByFornecedorId(@Param("idFornecedor") Long idFornecedor);

    public List<Fornecedor> findAllFornecedorByCodigoBarrasProduto(String codigoBarrasProduto);

}
