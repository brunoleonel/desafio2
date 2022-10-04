package br.com.dock.conta.core.exceptions;

public class ContaDuplicadaException extends DomainException{

    public ContaDuplicadaException(String message) {
        super(message);
    }
}
