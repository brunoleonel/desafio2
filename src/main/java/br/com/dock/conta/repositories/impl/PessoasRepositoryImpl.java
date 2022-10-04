package br.com.dock.conta.repositories.impl;

import br.com.dock.conta.adapters.PessoaAdapter;
import br.com.dock.conta.core.entities.Pessoa;
import br.com.dock.conta.domain.repositories.PessoasRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PessoasRepositoryImpl implements PessoasRepository {

    private EntityManager entityManager;
    private PessoaAdapter pessoaAdapter;

    public PessoasRepositoryImpl(EntityManager entityManager, PessoaAdapter pessoaAdapter) {
        this.entityManager = entityManager;
        this.pessoaAdapter = pessoaAdapter;
    }

    @Override
    public Pessoa buscarPorId(Long id) {
        var model = this.entityManager.find(br.com.dock.conta.models.hibernate.Pessoa.class, id);
        return this.pessoaAdapter.novaPessoa(model);
    }
}
