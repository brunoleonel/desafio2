package br.com.dock.conta.services.impl;

import br.com.dock.conta.core.entities.Conta;
import br.com.dock.conta.core.exceptions.ContaDuplicadaException;
import br.com.dock.conta.domain.repositories.ContasRepository;
import br.com.dock.conta.domain.services.ContaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ContaServiceImpl implements ContaService {

    private ContasRepository contasRepository;

    public ContaServiceImpl(ContasRepository contasRepository) {
        this.contasRepository = contasRepository;
    }

    @Transactional
    @Override
    public Conta criarConta(Conta conta) throws ContaDuplicadaException {
        this.validaContaExistente(conta.getPessoa().getIdPessoa(), conta.getTipoConta());
        this.contasRepository.criar(conta);
        return conta;
    }

    @Transactional
    @Override
    public void bloquearConta(Conta conta) {
        conta.bloquear();
        this.atualizar(conta);
    }

    @Override
    public BigDecimal consultarSaldo(Long idConta) {
        var conta= this.contasRepository.buscarPorId(idConta);
        return conta.getSaldo();
    }

    @Override
    public Conta buscarConta(Long idConta) {
        return this.contasRepository.buscarPorId(idConta);
    }

    @Transactional
    @Override
    public Conta atualizar(Conta conta) {
        this.contasRepository.atualizar(conta);
        return conta;
    }

    private void validaContaExistente(Long idConta, short tipoConta) throws ContaDuplicadaException {
        var conta= this.contasRepository.buscaContaDuplicada(idConta, tipoConta);
        if (conta != null) {
            throw new ContaDuplicadaException("Essa pessoa já possui uma conta desse tipo");
        }
    }
}
