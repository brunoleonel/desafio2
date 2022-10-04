package br.com.dock.conta.models.hibernate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Long idPessoa;

    @Column(nullable = false)
    private String nome;

    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    public Pessoa(Long idPessoa, String nome, String cpf, LocalDate dataNascimento) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa(br.com.dock.conta.core.entities.Pessoa entity) {
        this.idPessoa = entity.getIdPessoa();
        this.nome = entity.getNome();
        this.cpf = entity.getCpf();
        this.dataNascimento = entity.getDataNascimento();
    }

    public Pessoa() {}

    public Long getIdPessoa() {
        return idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
