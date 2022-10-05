package br.com.dock.conta.services.impl;

import br.com.dock.conta.core.entities.Conta;
import br.com.dock.conta.core.entities.TipoTransacao;
import br.com.dock.conta.core.entities.Transacao;
import br.com.dock.conta.core.exceptions.DomainException;
import br.com.dock.conta.core.exceptions.LimiteDeSaqueDiarioExcedidoException;
import br.com.dock.conta.domain.repositories.TransacoesRepository;
import br.com.dock.conta.domain.services.ContaService;
import br.com.dock.conta.domain.services.TransacoesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public Transacao criarTransacao(Transacao transacao) throws DomainException {
        var conta = transacao.getConta();
        conta.validarStatus();

        if (TipoTransacao.DEBITO.equals(transacao.getTipoTransacao())) {
            conta.validarSaldo(transacao.getValor());
            conta.validarLimiteSaqueDiario(transacao.getValor());
            this.validarValorTransacionadoDia(conta);
        }
        transacao.executa();
        contaService.atualizar(transacao.getConta());
        transacoesRepository.criar(transacao);
        return transacao;
    }

    @Override
    public BigDecimal calcularLimiteRestante(Conta conta) {
        var valor = this.transacoesRepository.consultaValorTransacionadoDia(conta.getIdConta());
        var restante = conta.getLimiteSaqueDiario().subtract(valor);
        return restante.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : restante;
    }

    @Override
    public List<Transacao> listarTransacoesPorPeriodo(Long idConta, Optional<LocalDate> dataInicial, Optional<LocalDate> dataFinal) {
        var dataInicio = dataInicial.orElse(LocalDate.now().minusDays(3));
        var dataFim = dataFinal.orElse(LocalDate.now());
        return this.transacoesRepository.listarTransacoesPorPeriodo(idConta, dataInicio, dataFim);
    }

    private void validarValorTransacionadoDia(Conta conta) throws LimiteDeSaqueDiarioExcedidoException {
        var valor = this.transacoesRepository.consultaValorTransacionadoDia(conta.getIdConta());
        var result = conta.getLimiteSaqueDiario().compareTo(valor);
        if (result <= 0) {
            throw new LimiteDeSaqueDiarioExcedidoException("Limite de saque diário excedido.");
        }
    }
}
