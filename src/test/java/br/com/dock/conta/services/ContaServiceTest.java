package br.com.dock.conta.services;

import br.com.dock.conta.core.entities.Conta;
import br.com.dock.conta.core.entities.Pessoa;
import br.com.dock.conta.core.exceptions.ContaDuplicadaException;
import br.com.dock.conta.repositories.impl.ContasRepositoryImpl;
import br.com.dock.conta.services.impl.ContaServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ContaServiceTest {

    @Test
    @DisplayName("Testa a criação de uma conta")
    public void testaCriacao() {
        var pessoa = Mockito.mock(Pessoa.class);
        Mockito.when(pessoa.getIdPessoa()).thenReturn(1L);

        var conta = Mockito.mock(Conta.class);
        Mockito.when(conta.getPessoa()).thenReturn(pessoa);
        Mockito.when(conta.getTipoConta()).thenReturn((short) 1);

        var repo = Mockito.mock(ContasRepositoryImpl.class);
        Mockito.when(repo.buscaContaDuplicada(1L, (short) 1)).thenReturn(null);
        var service = new ContaServiceImpl(repo);
        assertDoesNotThrow(() -> service.criarConta(conta));
    }

    @Test
    @DisplayName("Testa a validação de conta duplicada")
    public void testaContaDuplicada() {
        var pessoa = Mockito.mock(Pessoa.class);
        Mockito.when(pessoa.getIdPessoa()).thenReturn(1L);

        var conta = Mockito.mock(Conta.class);
        Mockito.when(conta.getPessoa()).thenReturn(pessoa);
        Mockito.when(conta.getTipoConta()).thenReturn((short) 1);

        var repo = Mockito.mock(ContasRepositoryImpl.class);
        Mockito.when(repo.buscaContaDuplicada(1L, (short) 1)).thenReturn(conta);
        var service = new ContaServiceImpl(repo);
        assertThrows(ContaDuplicadaException.class, () -> service.criarConta(conta));
    }

    @Test
    @DisplayName("Testa o bloqueio de uma conta")
    public void testaBloqueio() {
        var pessoa = Mockito.mock(Pessoa.class);
        var conta = new Conta(
                1L,
                pessoa,
                BigDecimal.ZERO,
                BigDecimal.TEN,
                true,
                (short) 1,
                LocalDate.now());
        var repo = Mockito.mock(ContasRepositoryImpl.class);
        var service = new ContaServiceImpl(repo);
        service.bloquearConta(conta);
        assertEquals(false, conta.isFlagAtivo());
    }
}
