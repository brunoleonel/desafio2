### Como rodar:
Para subir a aplicação verifique se o **Docker** e o **docker-compose** estão instalados no seu ambiente.
Se estiverem, rode o comando abaixo:
```
docker-compose -f ./docker/docker-compose.yml build
```
Após a finalização do build, rode:
```
docker-compose -f ./docker/docker-compose.yml up
```
**OBS:** Caso você tenha o PostgreSQL rodando na sua máquina, a porta do container do banco de dados pode
conflitar com a do serviço da sua máquina. Talvez seja necessário parar o serviço para conseguir rodar a aplicação.

* Os modelos de request estão na coleção **Contas.postman_collection.json** na raiz do projeto.
* As classes do domínio da aplicação estão documentadas no javadoc dentro de resources.

---

### Desafio Dock Tech de Seleção 
Olá, queremos convidá-lo a participar de nosso desafio de seleção.  Pronto para participar? Seu trabalho será visto por nosso time e você receberá ao final um feedback sobre o que achamos do seu trabalho. Não é legal?

### Sobre a oportunidade 
A vaga é para software engineer, temos vagas com diversos níveis de senioridade e para cada um deles utilizaremos critérios específicos considerando esse aspecto, combinado? 
Se você for aprovado nesta etapa, será convidado para uma entrevista final com nosso time de especialistas.

### Desafio Técnico
  Nós trabalhamos com meios de pagamento e nada melhor que um bom sistema para gestão de contas:
  
  - Pré-requisitos:
    ```
    * Desenvolver os recursos em API Rest que realizam operações bancárias com a entidade conta a seguir:
    ```
    | Contas | Tipo |
    |-|-|
    | idConta | Numérico |
    | idPessoa | Numérico |
    | saldo | Monetário |
    | limiteSaqueDiario | Monetário |
    | flagAtivo | Condicional |
    | tipoConta | Numérido |
    | dataCriacao | Data |

    ```
    * Tabela de transações realizadas na conta
    ```
    | Transacoes | Tipo |
    |-|-|
    | idTransacao | Numérico |
    | idConta | Numérico |
    | valor | Monetário |
    | dataTransacao | Data |

    ```
    * P.S.: Não é necessário realizar operações com a tabela pessoa, mas é necessária a criação da tabela para mapeamento da relação com a conta e enviar script de criação de pelo menos uma pessoa.
    ```

    | Pessoas | Tipo |
    |-|-|
    | idPessoa | Numérico |
    | nome | Texto |
    | cpf | Texto |
    | dataNascimento | Data |    

  - O que esperamos como escopo mínimo:
    ```
    * Implementar path que realiza a criação de uma conta;
    * Implementar path que realiza operação de depósito em uma conta;
    * Implementar path que realiza operação de consulta de saldo em determinada conta;
    * Implementar path que realiza operação de saque em uma conta;
    * Implementar path que realiza o bloqueio de uma conta;
    * Implementar path que recupera o extrato de transações de uma conta;
    ```
  - O que será diferencial:
    ```
    * Implementar extrato por período;
    * Elaborar manual de execução;
    * Elaborar documentação javadoc;
    * Elaborar testes;
    * Prazo de entrega;
    ```
    
  - O que vamos avaliar:
    ```
    * Seu código; 
    * Script de banco;
    * Organização;
    * Boas práticas;
    * Diferenciais;    
    ```


### Instruções
      1. Faça o fork do desafio;
      2. Desenvolva. Você terá até 3 (três) dias a partir da data do envio do desafio; 
      3. Envie um e-mail para arthur.azevedo@dock.tech notificando a finalização do desafio e o link do repositório para validação.
