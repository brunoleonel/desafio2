package br.com.dock.conta.core.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representação de transações (depósitos e saques) e seus comportamentos
 */
public class Transacao {

    protected Long idTransacao;
    protected Conta conta;
    protected BigDecimal valor;
    protected LocalDate dataTransacao;
    protected TipoTransacao tipoTransacao;

    public Transacao(
            Long idTransacao,
            Conta conta,
            BigDecimal valor,
            TipoTransacao tipoTransacao,
            LocalDate dataTransacao) {
        this.idTransacao = idTransacao;
        this.conta = conta;
        this.valor = valor;
        this.tipoTransacao = tipoTransacao;
        this.dataTransacao = dataTransacao;
    }

    /**
     * Executa a transação de débito (saque) ou crédito (depósito)
     */
    public void executa() {
        if (TipoTransacao.CREDITO.equals(this.tipoTransacao)) {
            this.conta.creditar(this.getValor());
            return;
        }

        this.conta.debitar(this.getValor());
    }

    public Long getIdTransacao() {
        return idTransacao;
    }

    public Conta getConta() {
        return conta;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public void setIdTransacao(Long idTransacao) {
        this.idTransacao = idTransacao;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
}
