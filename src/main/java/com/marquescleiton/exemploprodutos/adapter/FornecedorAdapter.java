package com.marquescleiton.exemploprodutos.adapter;

import com.marquescleiton.exemploprodutos.domain.entity.Fornecedor;
import com.marquescleiton.exemploprodutos.domain.entity.SituacaoFornecedor;
import com.marquescleiton.exemploprodutos.domain.entity.SituacaoFornecedorID;
import com.marquescleiton.exemploprodutos.domain.enums.SituacaoFornecedorEnum;

import java.time.LocalDateTime;
import java.util.Collections;

public class FornecedorAdapter {

    public Fornecedor dadosFornecedorDtoToNovoFornecedorEntity(Long idFornecedor, String codigoBarras){
        LocalDateTime dataHoraAtual = LocalDateTime.now();

        Fornecedor fornecedor = Fornecedor.builder()
                .idFornecedor(idFornecedor)
                .codigoBarrasProduto(codigoBarras)
                .dataCriacao(dataHoraAtual)
                .build();

        SituacaoFornecedor situacaoFornecedor = SituacaoFornecedor.builder()
                .situacaoFornecedorId(new SituacaoFornecedorID(fornecedor, SituacaoFornecedorEnum.ATIVO.getCodigo()))
                .dataCriacao(dataHoraAtual)
                .build();

        fornecedor.setSituacoesFornecedor(Collections.singletonList(situacaoFornecedor));

        return fornecedor;
    }
}
