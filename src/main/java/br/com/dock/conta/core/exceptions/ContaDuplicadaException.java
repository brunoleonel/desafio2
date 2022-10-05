package br.com.dock.conta.core.exceptions;

/**
 * Representa uma falha na criação da conta por duplicação. No caso já existe uma conta
 * do mesmo tipo para a mesma pessoa
 */
public class ContaDuplicadaException extends DomainException{

    public ContaDuplicadaException(String message) {
        super(message);
    }
}
