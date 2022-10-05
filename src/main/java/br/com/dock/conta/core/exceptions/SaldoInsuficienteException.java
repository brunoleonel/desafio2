package br.com.dock.conta.core.exceptions;

/**
 * Representa uma falha por saldo insuficiente
 */
public class SaldoInsuficienteException extends DomainException {

    public SaldoInsuficienteException(String message) {
        super(message);
    }
}
