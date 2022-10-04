package br.com.dock.conta.adapters;

import br.com.dock.conta.core.entities.Transacao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TransacaoAdapter {

    private ContaAdapter contaAdapter;

    public TransacaoAdapter(ContaAdapter contaAdapter) {
        this.contaAdapter = contaAdapter;
    }

    public void toEntity(br.com.dock.conta.models.hibernate.Transacao model, Transacao entity) {
        entity.setIdTransacao(model.getIdTransacao());
        entity.setConta(this.contaAdapter.novaConta(model.getConta()));
        entity.setValor(model.getValor());
        entity.setTipoTransacao(model.getTipoTransacao());
        entity.setDataTransacao(model.getDataTransacao());
    }

    public br.com.dock.conta.models.hibernate.Transacao toModel(Transacao entity) {
        return new br.com.dock.conta.models.hibernate.Transacao(
                entity.getIdTransacao(),
                this.contaAdapter.toModel(entity.getConta()),
                entity.getValor(),
                entity.getTipoTransacao(),
                entity.getDataTransacao());
    }

    public Transacao novaTransacao(br.com.dock.conta.models.hibernate.Transacao model) {
        return new Transacao(
                model.getIdTransacao(),
                this.contaAdapter.novaConta(model.getConta()),
                model.getValor(),
                model.getTipoTransacao(),
                model.getDataTransacao());
    }
}
