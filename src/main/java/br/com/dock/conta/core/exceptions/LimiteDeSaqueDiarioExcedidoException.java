package br.com.dock.conta.core.exceptions;

/**
 * Representa uma falha por limite de saque diário excedido.
 * Pode ocorrer por várias transações terem comprometido o limite, ou
 * uma única transação que ultrapassa o valor
 */
public class LimiteDeSaqueDiarioExcedidoException extends DomainException {

    public LimiteDeSaqueDiarioExcedidoException(String message) {
        super(message);
    }
}
