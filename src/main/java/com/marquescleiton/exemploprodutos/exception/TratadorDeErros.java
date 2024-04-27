package com.marquescleiton.exemploprodutos.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DadosErroValidacao("id_produto", "Produto não cadastrado"));
    }

    @ExceptionHandler(ProdutoNaoCadastradoException.class)
    public ResponseEntity trataProdutoNaoCadastrado(ProdutoNaoCadastradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DadosErroValidacao(ex.getCampo(), ex.getMensagem()));
    }

    @ExceptionHandler(ErroDeValidacaoException.class)
    public ResponseEntity tratarErro409(ErroDeValidacaoException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new DadosErroValidacao(ex.getCampo(), ex.getMensagem()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity tratarErro400(HttpMessageNotReadableException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DadosErroValidacao("id_produto", "O campo deve ser numérico até 15 dígitos"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro409(MethodArgumentNotValidException ex) {
        List<FieldError> erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity trataErro500(Exception ex){
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(new DadosErroValidacao(null, "Houve um erro interno. Por favor contatar a equipe técnica caso o erro persista!"));
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
