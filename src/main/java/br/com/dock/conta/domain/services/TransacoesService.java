package br.com.dock.conta.domain.services;

import br.com.dock.conta.core.entities.Conta;
import br.com.dock.conta.core.entities.Transacao;
import br.com.dock.conta.core.exceptions.DomainException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransacoesService {

    Transacao criarTransacao(Transacao transacao) throws DomainException;

    BigDecimal calcularLimiteRestante(Conta conta);

    List<Transacao> listarTransacoesPorPeriodo(Long idConta, Optional<LocalDate> dataInicial, Optional<LocalDate> dataFinal);
}
