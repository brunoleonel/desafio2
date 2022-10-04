package br.com.dock.conta.core.exceptions;

public class LimiteDeSaqueDiarioExcedidoException extends DomainException {

    public LimiteDeSaqueDiarioExcedidoException(String message) {
        super(message);
    }
}
