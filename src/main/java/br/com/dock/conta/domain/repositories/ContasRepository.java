package br.com.dock.conta.domain.repositories;

import br.com.dock.conta.core.entities.Conta;

public interface ContasRepository {
    Conta buscarPorId(Long idConta);
    void criar(Conta conta);
    void atualizar(Conta conta);
    Conta buscaContaDuplicada(Long idPessoa, short tipoConta);
}
