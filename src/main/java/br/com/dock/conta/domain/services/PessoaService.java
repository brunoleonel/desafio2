package br.com.dock.conta.domain.services;

import br.com.dock.conta.core.entities.Pessoa;

/**
 * Centraliza as regras e operações relativas às pessoas
 */
public interface PessoaService {

    /**
     * Consulta uma pessoa pelo id
     * @param idPessoa Id da pessoa
     * @return Pessoa
     */
    Pessoa buscarPorId(Long idPessoa);
}
