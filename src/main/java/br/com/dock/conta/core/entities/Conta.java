package br.com.dock.conta.core.entities;

import br.com.dock.conta.core.exceptions.ContaInativaException;
import br.com.dock.conta.core.exceptions.LimiteDeSaqueDiarioExcedidoException;
import br.com.dock.conta.core.exceptions.SaldoInsuficienteException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Conta {

    private Long idConta;
    private Pessoa pessoa;
    private BigDecimal saldo;
    private BigDecimal limiteSaqueDiario;
    private boolean flagAtivo;
    private short tipoConta;
    private LocalDate dataCriacao;

    public Conta(
            Pessoa pessoa,
            BigDecimal saldo,
            BigDecimal limiteSaqueDiario,
            boolean flagAtivo,
            short tipoConta,
            LocalDate dataCriacao) {
        this.pessoa = pessoa;
        this.saldo = saldo;
        this.limiteSaqueDiario = limiteSaqueDiario;
        this.flagAtivo = flagAtivo;
        this.tipoConta = tipoConta;
        this.dataCriacao = dataCriacao;
    }

    public Conta(
            Long idConta,
            Pessoa pessoa,
            BigDecimal saldo,
            BigDecimal limiteSaqueDiario,
            boolean flagAtivo,
            short tipoConta,
            LocalDate dataCriacao) {
        this.idConta = idConta;
        this.pessoa = pessoa;
        this.saldo = saldo;
        this.limiteSaqueDiario = limiteSaqueDiario;
        this.flagAtivo = flagAtivo;
        this.tipoConta = tipoConta;
        this.dataCriacao = dataCriacao;
    }

    public void validarStatus() throws ContaInativaException {
        if (!this.flagAtivo) {
            throw new ContaInativaException("Conta inativa");
        }
    }

    public void validarLimiteSaqueDiario(BigDecimal valor) throws LimiteDeSaqueDiarioExcedidoException {
        if (valor.compareTo(this.getLimiteSaqueDiario()) > 0) {
            throw new LimiteDeSaqueDiarioExcedidoException("Limite de saque diário excedido.");
        }
    }

    public void validarSaldo(BigDecimal valor) throws SaldoInsuficienteException {
        if (valor.compareTo(this.getSaldo()) > 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente.");
        }
    }

    public void creditar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public void debitar(BigDecimal valor) {
        this.saldo = this.saldo.subtract(valor);
    }

    public void bloquear() {
        this.flagAtivo = false;
    }

    public Long getIdConta() {
        return idConta;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal getLimiteSaqueDiario() {
        return limiteSaqueDiario;
    }

    public boolean isFlagAtivo() {
        return flagAtivo;
    }

    public short getTipoConta() {
        return tipoConta;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setLimiteSaqueDiario(BigDecimal limiteSaqueDiario) {
        this.limiteSaqueDiario = limiteSaqueDiario;
    }

    public void setFlagAtivo(boolean flagAtivo) {
        this.flagAtivo = flagAtivo;
    }

    public void setTipoConta(short tipoConta) {
        this.tipoConta = tipoConta;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
