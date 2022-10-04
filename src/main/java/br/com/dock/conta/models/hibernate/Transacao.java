package br.com.dock.conta.models.hibernate;

import br.com.dock.conta.core.entities.TipoTransacao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private Long idTransacao;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_conta")
    private Conta conta;

    @Column(nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transacao", nullable = false)
    private TipoTransacao tipoTransacao;

    @Column(name = "data_transacao", nullable = false)
    private LocalDate dataTransacao;

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

    public Transacao(br.com.dock.conta.core.entities.Transacao entity) {
        this.idTransacao = entity.getIdTransacao();
        this.conta = new Conta(entity.getConta());
        this.valor = entity.getValor();
        this.tipoTransacao = entity.getTipoTransacao();
        this.dataTransacao = entity.getDataTransacao();
    }

    public Transacao() {}

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
}
