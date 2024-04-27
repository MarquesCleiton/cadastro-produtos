package com.marquescleiton.exemploprodutos.repository;

import com.marquescleiton.exemploprodutos.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
    @Query(nativeQuery = true,value = "SELECT * FROM produto p where p.codigo_barras = :codigoBarras")
    public Produto testeBuscarSoProduto(@Param("codigoBarras") String codigoBarras);

    public Boolean existsByIdProduto(Long idProduto);

    @Query(nativeQuery = true,value =   "select * from produto p " +
                                        "WHERE p.id_produto = :idProduto " +
                                        "Order by p.data_criacao DESC limit 1;")
    public Optional<Produto> buscaProdutoMaisrecentePeloId(@Param("idProduto") Long idProduto);

    @Query(nativeQuery = true,value =   "select codigo_barras from produto p " +
            "WHERE p.id_produto = :idProduto " +
            "Order by p.data_criacao DESC limit 1;")
    public Optional<String> buscaCodigoBarrasProdutoMaisrecentePeloId(@Param("idProduto") Long idProduto);
}
