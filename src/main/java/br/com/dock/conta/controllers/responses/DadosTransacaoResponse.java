package br.com.dock.conta.controllers.responses;

import br.com.dock.conta.core.entities.TipoTransacao;
import br.com.dock.conta.core.entities.Transacao;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class DadosTransacaoResponse {

    @JsonProperty("id_transacao")
    private Long idTransacao;

    private BigDecimal valor;

    @JsonProperty("data_transacao")
    private String dataTransacao;

    @JsonProperty("tipo_transacao")
    private TipoTransacao tipoTransacao;

    public DadosTransacaoResponse(Transacao transacao) {
        this.idTransacao = transacao.getIdTransacao();
        this.valor = transacao.getValor();
        this.dataTransacao = transacao.getDataTransacao()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.tipoTransacao = transacao.getTipoTransacao();
    }

    public Long getIdTransacao() {
        return idTransacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDataTransacao() {
        return dataTransacao;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }
}
