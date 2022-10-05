package br.com.dock.conta.domain.repositories;

import br.com.dock.conta.core.entities.Conta;

/**
 * Centraliza as operações de consulta e persistência relativos à conta
 */
public interface ContasRepository {

    /**
     * Realiza a consulta de uma conta pelo id
     * @param idConta Id da conta
     * @return Conta
     */
    Conta buscarPorId(Long idConta);

    /**
     * Cria uma conta nova
     * @param conta Nova conta
     */
    void criar(Conta conta);

    /**
     * Atualiza uma conta que havia sido criada
     * @param conta Conta já existente
     */
    void atualizar(Conta conta);

    /**
     * Realiza a consulta de uma conta de um determinado tipo
     * para uma pessoa como forma de evitar duplicação
     * @param idPessoa Id da pessoa
     * @param tipoConta Tipo de conta: 1 ou 2
     * @return Conta caso ela exista
     */
    Conta buscaContaDuplicada(Long idPessoa, short tipoConta);
}
