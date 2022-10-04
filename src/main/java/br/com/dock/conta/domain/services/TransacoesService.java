package br.com.dock.conta.domain.services;

import br.com.dock.conta.core.entities.Transacao;
import br.com.dock.conta.core.exceptions.DomainException;

public interface TransacoesService {

    Transacao criaTransacao(Transacao transacao) throws DomainException;
}
