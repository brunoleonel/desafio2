package br.com.dock.conta.core.exceptions;

public class SaldoInsuficienteException extends DomainException {

    public SaldoInsuficienteException(String message) {
        super(message);
    }
}
