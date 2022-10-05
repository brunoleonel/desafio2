package br.com.dock.conta.domain.repositories;

import br.com.dock.conta.core.entities.Transacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Centraliza as operações de consulta e persistencia relativos às transações
 */
public interface TransacoesRepository {

    /**
     * Realiza a busca de uma transação por id
     * @param idTransacao Id da transação
     * @return Transação
     */
    Transacao buscarPorId(Long idTransacao);

    /**
     * Cria uma nova transação
     * @param transacao Transacao
     */
    void criar(Transacao transacao);

    /**
     * Consulta o valor transacionado durante o dia
     * @param idConta Id da conta
     * @return Conta
     */
    BigDecimal consultaValorTransacionadoDia(Long idConta);

    /**
     * Lista as transações realizadas dentro de um período
     * @param idConta Id da conta
     * @param dataInicial data de início do período
     * @param dataFinal data final do período
     * @return Lista de transações
     */
    List<Transacao> listarTransacoesPorPeriodo(Long idConta, LocalDate dataInicial, LocalDate dataFinal);
}
