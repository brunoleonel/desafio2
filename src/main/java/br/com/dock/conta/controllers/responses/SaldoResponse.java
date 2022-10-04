package br.com.dock.conta.controllers.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class SaldoResponse {

    @JsonProperty("saldo_atual")
    private BigDecimal saldo;
    @JsonProperty("disponivel_para_saque_hoje")
    private BigDecimal disponivelParaSaque;

    public SaldoResponse(BigDecimal saldo, BigDecimal disponivelParaSaque) {
        this.saldo = saldo;
        this.disponivelParaSaque = disponivelParaSaque;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal getDisponivelParaSaque() {
        return disponivelParaSaque;
    }
}
