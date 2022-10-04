package br.com.dock.conta.controllers.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class TransacaoRequest {

    @NotNull(message = "O valor da transação e obrigatório.")
    @Positive(message = "Informe um valor maior que zero.")
    private BigDecimal valor;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
