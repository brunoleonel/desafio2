package br.com.dock.conta.controllers.responses;

import br.com.dock.conta.core.entities.Transacao;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class ExtratoResponse {

    @JsonProperty("valores_em_conta")
    private SaldoResponse valores;

    private List<DadosTransacaoResponse> transacoes;

    public ExtratoResponse(BigDecimal saldo, BigDecimal limite, List<Transacao> transacoes) {
        this.valores = new SaldoResponse(saldo, limite);
        this.transacoes = transacoes.stream()
                .map(t -> new DadosTransacaoResponse(t))
                .toList();
    }

    public SaldoResponse getValores() {
        return valores;
    }

    public List<DadosTransacaoResponse> getTransacoes() {
        return transacoes;
    }
}
