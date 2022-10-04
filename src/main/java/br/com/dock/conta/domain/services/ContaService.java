package br.com.dock.conta.domain.services;

import br.com.dock.conta.core.entities.Conta;
import br.com.dock.conta.core.exceptions.ContaDuplicadaException;

import java.math.BigDecimal;

public interface ContaService {

    Conta criarConta(Conta conta) throws ContaDuplicadaException;
    void bloquearConta(Conta conta);
    BigDecimal consultarSaldo(Long idConta);
    Conta buscarConta(Long idConta);
    Conta atualizar(Conta conta);
}
