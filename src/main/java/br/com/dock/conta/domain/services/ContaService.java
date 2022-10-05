package br.com.dock.conta.domain.services;

import br.com.dock.conta.core.entities.Conta;
import br.com.dock.conta.core.exceptions.ContaDuplicadaException;

import java.math.BigDecimal;

/**
 * Centraliza regras e operações relativas às contas
 */
public interface ContaService {

    /**
     * Cria uma nova conta
     *
     * @param conta objeto de conta com os dados
     * @return Conta criada
     * @throws ContaDuplicadaException Caso uma conta do mesmo tipo para a mesma pessoa já exista
     */
    Conta criarConta(Conta conta) throws ContaDuplicadaException;

    /**
     * Realiza o bloqueio de uma conta
     * @param conta Conta a ser bloqueada
     */
    void bloquearConta(Conta conta);

    /**
     * Consulta o saldo de uma conta
     * @param idConta Id da conta
     * @return Valor do saldo
     */
    BigDecimal consultarSaldo(Long idConta);

    /**
     * Consulta uma conta pelo id
     * @param idConta Id da conta
     * @return Conta
     */
    Conta buscarConta(Long idConta);

    /**
     * Atualiza uma conta já criada
     * @param conta Conta
     * @return A própria conta
     */
    Conta atualizar(Conta conta);
}
