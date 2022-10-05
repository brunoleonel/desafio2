package br.com.dock.conta.domain.repositories;

import br.com.dock.conta.core.entities.Pessoa;

/**
 * Centraliza as operações de consulta relativas à pessoa
 */
public interface PessoasRepository {

    /**
     * Realiza a consulta de uma pessoa por Id
     * @param idPessoa Id da pessoa
     * @return Pessoa
     */
    Pessoa buscarPorId(Long idPessoa);
}
