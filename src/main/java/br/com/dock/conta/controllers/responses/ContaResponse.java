package br.com.dock.conta.controllers.responses;

import br.com.dock.conta.core.entities.Conta;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ContaResponse {

    @JsonProperty("id_conta")
    private Long idConta;

    @JsonProperty("id_pessoa")
    private Long idPessoa;

    @JsonProperty("limite_saque_diario")
    private BigDecimal limiteSaqueDiario;

    @JsonProperty("ativo")
    private boolean ativo;

    @JsonProperty("tipo_conta")
    private int tipoConta;

    public ContaResponse(Conta conta) {
        this.idConta = conta.getIdConta();
        this.idPessoa = conta.getPessoa().getIdPessoa();
        this.limiteSaqueDiario = conta.getLimiteSaqueDiario();
        this.ativo = conta.isFlagAtivo();
        this.tipoConta = conta.getTipoConta();
    }

    public Long getIdConta() {
        return idConta;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public BigDecimal getLimiteSaqueDiario() {
        return limiteSaqueDiario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public int getTipoConta() {
        return tipoConta;
    }
}
