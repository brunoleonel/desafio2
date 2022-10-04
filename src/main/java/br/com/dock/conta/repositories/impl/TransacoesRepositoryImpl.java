package br.com.dock.conta.repositories.impl;

import br.com.dock.conta.adapters.TransacaoAdapter;
import br.com.dock.conta.core.entities.Transacao;
import br.com.dock.conta.domain.repositories.TransacoesRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class TransacoesRepositoryImpl implements TransacoesRepository {

    private EntityManager entityManager;
    private TransacaoAdapter transacaoAdapter;

    public TransacoesRepositoryImpl(EntityManager entityManager, TransacaoAdapter transacaoAdapter) {
        this.entityManager = entityManager;
        this.transacaoAdapter = transacaoAdapter;
    }

    @Override
    public Transacao buscarPorId(Long id) {
        var model = this.entityManager.find(br.com.dock.conta.models.hibernate.Transacao.class, id);
        return this.transacaoAdapter.novaTransacao(model);
    }

    @Override
    public void criar(Transacao transacao) {
        var model = this.transacaoAdapter.toModel(transacao);
        this.entityManager.persist(model);
        this.transacaoAdapter.toEntity(model, transacao);
    }

    @Override
    public void atualizar(Transacao transacao) {
        var model = this.transacaoAdapter.toModel(transacao);
        this.entityManager.merge(transacao);
        this.transacaoAdapter.toEntity(model, transacao);
    }
}
