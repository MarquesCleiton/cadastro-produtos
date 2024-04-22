package com.marquescleiton.exemploprodutos.web.controller;

import com.marquescleiton.exemploprodutos.domain.dto.DadosAtualizacaoSituacaoFornecedor;
import com.marquescleiton.exemploprodutos.domain.dto.DadosForncedor;
import com.marquescleiton.exemploprodutos.domain.dto.DadosProduto;
import com.marquescleiton.exemploprodutos.domain.dto.DadosStatus;
import com.marquescleiton.exemploprodutos.domain.entity.Produto;
import com.marquescleiton.exemploprodutos.usecase.UseCase;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produto")
@AllArgsConstructor
public class Controller {
    private final UseCase useCaseProduto;

    @PostMapping("criar")
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody @Validated DadosProduto dadosProduto){
        return ResponseEntity.status(HttpStatus.CREATED).body(useCaseProduto.cadastrarProduto(dadosProduto));
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<Produto> buscarProduto(
            @Validated
            @Digits(integer = 15, message = "deve ter no máximo 15 dígitos", fraction = 0)
            @PathVariable
            Long idProduto){
        return ResponseEntity.ok(useCaseProduto.buscarProdutoPeloId(idProduto));
    }

    @PostMapping("atualizar")
    public ResponseEntity<Produto> atualizarProduto(@RequestBody @Validated DadosStatus dadosStatus) {
        return ResponseEntity.status(HttpStatus.CREATED).body(useCaseProduto.atualizarStatusProduto(dadosStatus));
    }

    @PostMapping("fornecedor")
    public ResponseEntity<Produto> cadastrarFornecedor(@RequestBody @Validated DadosForncedor dadosForncedor){
        return ResponseEntity.status(HttpStatus.CREATED).body(useCaseProduto.cadastrarNovoForcedor(dadosForncedor));
    }

    @GetMapping("/fornecedor/{idFornecedor}")
    public ResponseEntity<Produto> buscarFornecedor(
            @Validated
            @Digits(integer = 15, message = "deve ter no máximo 15 dígitos", fraction = 0)
            @PathVariable
            Long idFornecedor){

        return ResponseEntity.ok(useCaseProduto.buscarFornecedor(idFornecedor));
    }

    @PostMapping("/fornecedor/atualizar")
    public ResponseEntity<Produto> atualizarFornecedor(@RequestBody @Validated DadosAtualizacaoSituacaoFornecedor situacaoFornecedor){
        return ResponseEntity.status(HttpStatus.CREATED).body(useCaseProduto.atualizarStatusFornecedor(situacaoFornecedor));
    }

}
