package br.com.dock.conta.controllers.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ContaRequest {

    @NotNull(message = "O id da pessoa é obrigatório.")
    @Positive(message = "Forneça um valor válido.")
    @JsonProperty("id_pessoa")
    private Long idPessoa;

    @NotNull(message = "O limite de saque diário é obrigatório.")
    @Positive(message = "Forneça um valor válido.")
    @JsonProperty("limite_saque_diario")
    private BigDecimal limiteSaqueDiario;

    @NotNull(message = "O tipo de conta é obrigatório.")
    @Min(value = 1, message = "Informe 1 para conta corrente ou 2 para poupança.")
    @Max(value = 2, message = "Informe 1 para conta corrente ou 2 para poupança.")
    @JsonProperty("tipo_conta")
    private short tipoConta;

    public Long getIdPessoa() {
        return idPessoa;
    }

    public BigDecimal getLimiteSaqueDiario() {
        return limiteSaqueDiario;
    }

    public short getTipoConta() {
        return tipoConta;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public void setLimiteSaqueDiario(BigDecimal limiteSaqueDiario) {
        this.limiteSaqueDiario = limiteSaqueDiario;
    }

    public void setTipoConta(short tipoConta) {
        this.tipoConta = tipoConta;
    }
}
