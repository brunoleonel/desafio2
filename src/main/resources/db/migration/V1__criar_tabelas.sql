CREATE TABLE pessoas (
    id_pessoa BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    data_nascimento DATE NOT NULL,
    CONSTRAINT pk_pessoas PRIMARY KEY (id_pessoa)
);

CREATE TABLE contas (
    id_conta BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_pessoa BIGINT NOT NULL,
    saldo NUMERIC(7,2) NOT NULL,
    limite_saque_diario NUMERIC(7,2) NOT NULL,
    ativo BOOLEAN NOT NULL,
    tipo_conta SMALLINT NOT NULL,
    data_criacao DATE NOT NULL,
    CONSTRAINT pk_contas PRIMARY KEY (id_conta),
    CONSTRAINT fk_pessoa FOREIGN KEY (id_pessoa) REFERENCES pessoas(id_pessoa)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE transacoes (
    id_transacao BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_conta BIGINT NOT NULL,
    valor NUMERIC(7,2) NOT NULL,
    tipo_transacao VARCHAR(7) NOT NULL,
    data_transacao DATE NOT NULL,
    CONSTRAINT pk_transacoes PRIMARY KEY (id_transacao),
    CONSTRAINT fk_conta FOREIGN KEY (id_conta) references contas(id_conta)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);