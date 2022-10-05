package br.com.dock.conta.core.exceptions;

/**
 * Representa uma falha numa transação pela conta estar bloqueada
 */
public class ContaInativaException extends DomainException {

    public ContaInativaException(String message) {
        super(message);
    }
}
