package br.com.dock.conta.adapters;

import br.com.dock.conta.core.entities.Pessoa;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PessoaAdapter {

    public void toEntity(br.com.dock.conta.models.hibernate.Pessoa model, Pessoa entity) {
        entity.setIdPessoa(model.getIdPessoa());
        entity.setNome(model.getNome());
        entity.setCpf(model.getCpf());
        entity.setDataNascimento(model.getDataNascimento());
    }

    public br.com.dock.conta.models.hibernate.Pessoa toModel(Pessoa entity) {
        return new br.com.dock.conta.models.hibernate.Pessoa(
                entity.getIdPessoa(),
                entity.getNome(),
                entity.getCpf(),
                entity.getDataNascimento());
    }

    public Pessoa novaPessoa(br.com.dock.conta.models.hibernate.Pessoa model) {
        return new Pessoa(
                model.getIdPessoa(),
                model.getNome(),
                model.getCpf(),
                model.getDataNascimento());
    }
}
