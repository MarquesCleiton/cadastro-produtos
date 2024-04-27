package com.marquescleiton.exemploprodutos.web.controller;

import com.marquescleiton.exemploprodutos.domain.dto.DadosAtualizacaoSituacaoFornecedor;
import com.marquescleiton.exemploprodutos.domain.dto.DadosForncedor;
import com.marquescleiton.exemploprodutos.domain.dto.DadosProduto;
import com.marquescleiton.exemploprodutos.domain.dto.DadosStatus;
import com.marquescleiton.exemploprodutos.domain.entity.Produto;
import com.marquescleiton.exemploprodutos.usecase.controller.ControllerUseCase;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produto")
@AllArgsConstructor
public class Controller {
    private final ControllerUseCase controllerUseCase;


    @PostMapping("criar")
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody @Validated DadosProduto dadosProduto){
        return ResponseEntity.status(HttpStatus.CREATED).body(controllerUseCase.cadastrarProduto(dadosProduto));
    }

    @GetMapping("/{codigoBarras}")
    public ResponseEntity<Produto> buscarProduto(
            @Validated
            @NotBlank
            @Size(min = 21, max = 21, message = "campo deve ter exatamente 21 dígitos")
            @PathVariable
            String codigoBarras){
        return ResponseEntity.ok(controllerUseCase.buscarProduto(codigoBarras));
    }

    @PostMapping("atualizar")
    public ResponseEntity<Produto> atualizarProduto(@RequestBody @Validated DadosStatus dadosStatus) {
        return ResponseEntity.status(HttpStatus.CREATED).body(controllerUseCase.atualizarProduto(dadosStatus));
    }

    @PostMapping("fornecedor")
    public ResponseEntity<Produto> cadastrarFornecedor(@RequestBody @Validated DadosForncedor dadosForncedor){
        return ResponseEntity.status(HttpStatus.CREATED).body(controllerUseCase.cadastrarFornecedor(dadosForncedor));
    }

    @GetMapping("/fornecedor/{idFornecedor}")
    public ResponseEntity<Produto> buscarFornecedor(
            @Validated
            @Digits(integer = 15, message = "deve ter no máximo 15 dígitos", fraction = 0)
            @PathVariable
            Long idFornecedor){

        return ResponseEntity.ok(controllerUseCase.buscarFornecedor(idFornecedor));
    }

    @PostMapping("/fornecedor/atualizar")
    public ResponseEntity<Produto> atualizarFornecedor(@RequestBody @Validated DadosAtualizacaoSituacaoFornecedor situacaoFornecedor){
        return ResponseEntity.status(HttpStatus.CREATED).body(controllerUseCase.atualizarFornecedor(situacaoFornecedor));
    }

}
