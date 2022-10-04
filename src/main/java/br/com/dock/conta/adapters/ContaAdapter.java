package br.com.dock.conta.adapters;

import br.com.dock.conta.core.entities.Conta;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ContaAdapter {

    private PessoaAdapter pessoaAdapter;

    public ContaAdapter(PessoaAdapter pessoaAdapter) {
        this.pessoaAdapter = pessoaAdapter;
    }

    public void toEntity(br.com.dock.conta.models.hibernate.Conta model, Conta entity) {
        entity.setIdConta(model.getIdConta());
        entity.setPessoa(this.pessoaAdapter.novaPessoa(model.getPessoa()));
        entity.setSaldo(model.getSaldo());
        entity.setLimiteSaqueDiario(model.getLimiteSaqueDiario());
        entity.setFlagAtivo(model.isFlagAtivo());
        entity.setTipoConta(model.getTipoConta());
        entity.setDataCriacao(model.getDataCriacao());
    }

    public br.com.dock.conta.models.hibernate.Conta toModel(Conta entity) {
        return new br.com.dock.conta.models.hibernate.Conta(
                entity.getIdConta(),
                this.pessoaAdapter.toModel(entity.getPessoa()),
                entity.getSaldo(),
                entity.getLimiteSaqueDiario(),
                entity.isFlagAtivo(),
                entity.getTipoConta(),
                entity.getDataCriacao());
    }

    public Conta novaConta(br.com.dock.conta.models.hibernate.Conta model) {
        return new Conta(
                model.getIdConta(),
                this.pessoaAdapter.novaPessoa(model.getPessoa()),
                model.getSaldo(),
                model.getLimiteSaqueDiario(),
                model.isFlagAtivo(),
                model.getTipoConta(),
                model.getDataCriacao());
    }
}
