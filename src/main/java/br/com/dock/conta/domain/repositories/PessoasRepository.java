package br.com.dock.conta.domain.repositories;

import br.com.dock.conta.core.entities.Pessoa;

public interface PessoasRepository {
    Pessoa buscarPorId(Long idPessoa);
    void criar(Pessoa pessoa);
    void atualizar(Pessoa pessoa);
}
