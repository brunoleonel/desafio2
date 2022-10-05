package br.com.dock.conta.repositories.impl;

import br.com.dock.conta.adapters.TransacaoAdapter;
import br.com.dock.conta.core.entities.TipoTransacao;
import br.com.dock.conta.core.entities.Transacao;
import br.com.dock.conta.domain.repositories.TransacoesRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class TransacoesRepositoryImpl implements TransacoesRepository {

    private EntityManager entityManager;
    private TransacaoAdapter transacaoAdapter;

    public TransacoesRepositoryImpl(EntityManager entityManager, TransacaoAdapter transacaoAdapter) {
        this.entityManager = entityManager;
        this.transacaoAdapter = transacaoAdapter;
    }

    @Override
    public Transacao buscarPorId(Long id) {
        var model = this.entityManager.find(br.com.dock.conta.models.hibernate.Transacao.class, id);
        return this.transacaoAdapter.novaTransacao(model);
    }

    @Override
    public void criar(Transacao transacao) {
        var model = this.transacaoAdapter.toModel(transacao);
        this.entityManager.persist(model);
        this.transacaoAdapter.toEntity(model, transacao);
    }

    @Override
    public BigDecimal consultaValorTransacionadoDia(Long idConta) {
        var valor= this.entityManager.createQuery(
                """
                        SELECT SUM(t.valor) FROM Transacao t 
                        WHERE t.conta.idConta = :idConta
                            AND t.dataTransacao = :dataTransacao
                            AND t.tipoTransacao = :tipoTransacao
                        GROUP BY t.conta.idConta""",
                        BigDecimal.class)
                .setParameter("idConta", idConta)
                .setParameter("dataTransacao", LocalDate.now())
                .setParameter("tipoTransacao", TipoTransacao.DEBITO)
                .getResultStream()
                .findFirst()
                .orElse(BigDecimal.ZERO);

        return valor;
    }

    @Override
    public List<Transacao> listarTransacoesPorPeriodo(Long idConta, LocalDate dataInicial, LocalDate dataFinal) {
        return this.entityManager.createQuery(
                """
                        SELECT t FROM Transacao t
                        WHERE t.conta.idConta = :idConta
                        AND t.dataTransacao BETWEEN :dataInicio AND :dataFim""",
                        br.com.dock.conta.models.hibernate.Transacao.class)
                .setParameter("idConta", idConta)
                .setParameter("dataInicio", dataInicial)
                .setParameter("dataFim", dataFinal)
                .getResultStream()
                .map(t -> this.transacaoAdapter.novaTransacao(t))
                .toList();
    }
}
