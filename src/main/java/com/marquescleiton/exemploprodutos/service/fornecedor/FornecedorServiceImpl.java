package com.marquescleiton.exemploprodutos.service.fornecedor;

import com.marquescleiton.exemploprodutos.domain.entity.Fornecedor;
import com.marquescleiton.exemploprodutos.exception.FornecedorNaoCadastradoException;
import com.marquescleiton.exemploprodutos.repository.FornecedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FornecedorServiceImpl implements FornecedorService{

    @Autowired
    FornecedorRepository fornecedorRepository;

    @Override
    public Boolean isFornecedorCadastradoParaProduto(Long idFornecedor, String codigoBarras) {
        return fornecedorRepository.existsByIdFornecedorAndCodigoBarrasProduto(idFornecedor, codigoBarras);
    }

    @Override
    @Transactional
    public void cadastrarFornecedor(Fornecedor fornecedor) {
        fornecedorRepository.save(fornecedor);
    }

    @Override
    public Fornecedor buscarFornecedorPeloId(Long idFornecedor) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(idFornecedor);

        if(Boolean.FALSE.equals(fornecedor.isPresent())){
            throw new FornecedorNaoCadastradoException(null, "Fornecedor " + idFornecedor + " não cadastrado");
        }

        return fornecedor.get();
    }

    @Override
    public List<Fornecedor> buscarFornecedoresPeloCodigoBarrasProduto(String codigoBarras) {
        return fornecedorRepository.findAllFornecedorByCodigoBarrasProduto(codigoBarras);
    }
}
