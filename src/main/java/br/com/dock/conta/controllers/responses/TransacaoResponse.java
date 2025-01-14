package br.com.dock.conta.controllers.responses;

import br.com.dock.conta.core.entities.Transacao;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransacaoResponse {

    @JsonProperty("id_transacao")
    private Long idTransacao;

    private BigDecimal valor;

    @JsonProperty("saldo_atual")
    private BigDecimal saldoAtual;

    @JsonProperty("data_transacao")
    private String dataTransacao;

    public TransacaoResponse(Long idTransacao, BigDecimal valor, BigDecimal saldoAtual, LocalDate dataTransacao) {
        this.idTransacao = idTransacao;
        this.valor = valor;
        this.saldoAtual = saldoAtual;
        this.dataTransacao = dataTransacao.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public TransacaoResponse(Transacao transacao) {
        this.idTransacao = transacao.getIdTransacao();
        this.valor = transacao.getValor();
        this.saldoAtual = transacao.getConta().getSaldo();
        this.dataTransacao = transacao.getDataTransacao()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public Long getIdTransacao() {
        return idTransacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public BigDecimal getSaldoAtual() {
        return saldoAtual;
    }

    public String getDataTransacao() {
        return dataTransacao;
    }
}
