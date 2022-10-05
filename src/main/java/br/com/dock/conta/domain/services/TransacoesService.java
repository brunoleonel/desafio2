package br.com.dock.conta.domain.services;

import br.com.dock.conta.core.entities.Conta;
import br.com.dock.conta.core.entities.Transacao;
import br.com.dock.conta.core.exceptions.DomainException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Centraliza as regras e operações relativas às transações
 */
public interface TransacoesService {

    /**
     * Cria uma nova transação
     * @param transacao objeto de transação com os dados
     * @return Nova transação
     * @throws DomainException Caso uma das valiações falhe
     */
    Transacao criarTransacao(Transacao transacao) throws DomainException;

    /**
     * Calcula o limite restante no dia após todas as transações que ocorreram
     * @param conta Conta
     * @return Valor restante do limite
     */
    BigDecimal calcularLimiteRestante(Conta conta);

    /**
     * Lista as transações dentro de um período
     * @param idConta Id da conta
     * @param dataInicial Data inicial do período
     * @param dataFinal Data final do período
     * @return Lista de transações
     */
    List<Transacao> listarTransacoesPorPeriodo(Long idConta, Optional<LocalDate> dataInicial, Optional<LocalDate> dataFinal);
}
