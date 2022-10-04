package br.com.dock.conta.services.impl;

import br.com.dock.conta.core.entities.Pessoa;
import br.com.dock.conta.domain.repositories.PessoasRepository;
import br.com.dock.conta.domain.services.PessoaService;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl implements PessoaService {

    private PessoasRepository pessoasRepository;

    public PessoaServiceImpl(PessoasRepository pessoasRepository) {
        this.pessoasRepository = pessoasRepository;
    }

    @Override
    public Pessoa buscarPorId(Long idPessoa) {
        return this.pessoasRepository.buscarPorId(idPessoa);
    }
}
