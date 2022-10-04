package br.com.dock.conta.models.hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Long idConta;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

    @Column(nullable = false)
    private BigDecimal saldo;

    @Column(name = "limite_saque_diario", nullable = false)
    private BigDecimal limiteSaqueDiario;

    @Column(name = "ativo", nullable = false)
    private boolean flagAtivo;

    @Column(name = "tipo_conta", nullable = false)
    private short tipoConta;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    public Conta(Long idConta,
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

    public Conta(br.com.dock.conta.core.entities.Conta entity) {
        this.idConta = entity.getIdConta();
        this.pessoa = new Pessoa(entity.getPessoa());
        this.saldo = entity.getSaldo();
        this.limiteSaqueDiario = entity.getLimiteSaqueDiario();
        this.flagAtivo = entity.isFlagAtivo();
        this.tipoConta = entity.getTipoConta();
        this.dataCriacao = entity.getDataCriacao();
    }

    public Conta() {
    }

    public Long getIdConta() {
        return idConta;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
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
}
