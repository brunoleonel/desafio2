package br.com.dock.conta;

import br.com.dock.conta.adapters.ContaAdapter;
import br.com.dock.conta.adapters.PessoaAdapter;
import br.com.dock.conta.adapters.TransacaoAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ContaApplication {
	public static void main(String[] args) {
		SpringApplication.run(ContaApplication.class, args);
	}
}
