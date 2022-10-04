package br.com.dock.conta.services.impl;

import br.com.dock.conta.core.entities.TipoTransacao;
import br.com.dock.conta.core.entities.Transacao;
import br.com.dock.conta.core.exceptions.DomainException;
import br.com.dock.conta.domain.repositories.TransacoesRepository;
import br.com.dock.conta.domain.services.ContaService;
import br.com.dock.conta.domain.services.TransacoesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransacoesServiceImpl implements TransacoesService {

    private TransacoesRepository transacoesRepository;
    private ContaService contaService;

    public TransacoesServiceImpl(TransacoesRepository transacoesRepository, ContaService contaService) {
        this.transacoesRepository = transacoesRepository;
        this.contaService = contaService;
    }

    @Transactional
    @Override
    public Transacao criaTransacao(Transacao transacao) throws DomainException {
        var conta = transacao.getConta();
        conta.validarStatus();

        if (TipoTransacao.DEBITO.equals(transacao.getTipoTransacao())) {
            conta.validarSaldo(transacao.getValor());
            conta.validarLimiteSaqueDiario(transacao.getValor());
        }
        transacao.executa();
        contaService.atualizar(transacao.getConta());
        transacoesRepository.criar(transacao);
        return transacao;
    }
}
