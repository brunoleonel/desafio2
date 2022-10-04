package br.com.dock.conta.repositories.impl;

import br.com.dock.conta.adapters.ContaAdapter;
import br.com.dock.conta.core.entities.Conta;
import br.com.dock.conta.domain.repositories.ContasRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ContasRepositoryImpl implements ContasRepository {

    private EntityManager entityManager;
    private ContaAdapter contaAdapter;

    public ContasRepositoryImpl(EntityManager entityManager, ContaAdapter contaAdapter) {
        this.entityManager = entityManager;
        this.contaAdapter = contaAdapter;
    }

    @Override
    public Conta buscarPorId(Long id) {
        var model = this.entityManager.find(br.com.dock.conta.models.hibernate.Conta.class, id);
        return this.contaAdapter.novaConta(model);
    }

    @Override
    public void criar(Conta conta) {
        var model = this.contaAdapter.toModel(conta);
        this.entityManager.persist(model);
        this.contaAdapter.toEntity(model, conta);
    }

    @Override
    public void atualizar(Conta conta) {
        var model = this.contaAdapter.toModel(conta);
        this.entityManager.merge(model);
        this.contaAdapter.toEntity(model, conta);
    }

    @Override
    public Conta buscaContaDuplicada(Long idPessoa, short tipoConta) {
        var result = entityManager.createQuery(
                "SELECT c FROM Conta c WHERE c.pessoa.idPessoa = :idPessoa AND c.tipoConta = :tipoConta",
                        br.com.dock.conta.models.hibernate.Conta.class)
                .setParameter("idPessoa", idPessoa)
                .setParameter("tipoConta", tipoConta)
                .getResultStream()
                .findFirst()
                .orElse(null);
        return result != null ? this.contaAdapter.novaConta(result) : null;
    }
}
