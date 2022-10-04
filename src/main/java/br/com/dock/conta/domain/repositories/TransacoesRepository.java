package br.com.dock.conta.domain.repositories;

import br.com.dock.conta.core.entities.Transacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransacoesRepository {
    Transacao buscarPorId(Long idTransacao);

    void criar(Transacao transacao);

    BigDecimal consultaValorTransacionadoDia(Long idConta);

    List<Transacao> listarTransacoesPorPeriodo(Long idConta, LocalDate dataInicial, LocalDate dataFinal);
}
