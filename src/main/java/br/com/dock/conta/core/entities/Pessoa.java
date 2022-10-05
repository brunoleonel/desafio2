package br.com.dock.conta.core.entities;

import java.time.LocalDate;

/**
 * Representação de uma pessoa
 */
public class Pessoa {

    private Long idPessoa;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    public Pessoa(Long idPessoa, String nome, String cpf, LocalDate dataNascimento) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

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

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
