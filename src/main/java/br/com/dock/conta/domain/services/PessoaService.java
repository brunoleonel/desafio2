package br.com.dock.conta.domain.services;

import br.com.dock.conta.core.entities.Pessoa;

public interface PessoaService {

    Pessoa buscarPorId(Long idPessoa);
}
