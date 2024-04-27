package com.marquescleiton.exemploprodutos.service.fornecedor;

import com.marquescleiton.exemploprodutos.domain.entity.Fornecedor;
import com.marquescleiton.exemploprodutos.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FornecedorServiceImpl implements FornecedorService{

    @Autowired
    FornecedorRepository fornecedorRepository;

    @Override
    public Boolean isFornecedorCadastradoParaProduto(Long idFornecedor, String codigoBarras) {
        return fornecedorRepository.existsByIdFornecedorAndCodigoBarrasProduto(idFornecedor, codigoBarras);
    }

    @Override
    public void cadastrarNovoFornecedor(Fornecedor fornecedor) {
        fornecedorRepository.save(fornecedor);
    }
}
