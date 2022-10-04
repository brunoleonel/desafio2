package br.com.dock.conta.domain.repositories;

import br.com.dock.conta.core.entities.Transacao;
import org.springframework.transaction.annotation.Transactional;

public interface TransacoesRepository {
    Transacao buscarPorId(Long idTransacao);
    void criar(Transacao transacao);
    void atualizar(Transacao transacao);
}
