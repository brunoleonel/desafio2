package br.com.dock.conta.services;

import br.com.dock.conta.core.entities.Conta;
import br.com.dock.conta.core.entities.Pessoa;
import br.com.dock.conta.core.entities.TipoTransacao;
import br.com.dock.conta.core.entities.Transacao;
import br.com.dock.conta.core.exceptions.DomainException;
import br.com.dock.conta.core.exceptions.LimiteDeSaqueDiarioExcedidoException;
import br.com.dock.conta.domain.services.ContaService;
import br.com.dock.conta.repositories.impl.TransacoesRepositoryImpl;
import br.com.dock.conta.services.impl.TransacoesServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.doAnswer;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TransacoesServiceTest {

    @Test
    @DisplayName("Testa a criação de uma transação - depósito")
    public void testaCriacaoDeposito() throws DomainException {
        var pessoa = Mockito.mock(Pessoa.class);
        var conta = new Conta(
                1L,
                pessoa,
                BigDecimal.ZERO,
                BigDecimal.TEN,
                true,
                (short) 1,
                LocalDate.now());
        var transacao = new Transacao(
                null,
                conta,
                BigDecimal.TEN,
                TipoTransacao.CREDITO,
                LocalDate.now());

        var contaService = Mockito.mock(ContaService.class);

        Mockito.when(contaService.atualizar(conta)).thenReturn(conta);
        var repo = Mockito.mock(TransacoesRepositoryImpl.class);
        doAnswer(t -> {
            transacao.setIdTransacao(1L);
            return null;
        }).when(repo).criar(transacao);

        var service = new TransacoesServiceImpl(repo, contaService);
        var result = service.criarTransacao(transacao);
        assertEquals(BigDecimal.TEN, conta.getSaldo());
        assertEquals(1L, result.getIdTransacao());
    }
    @Test
    @DisplayName("Testa a criação de uma transação - saque")
    public void testaCriacaoSaque() throws DomainException {
        var pessoa = Mockito.mock(Pessoa.class);
        var conta = new Conta(
                1L,
                pessoa,
                BigDecimal.TEN,
                BigDecimal.TEN,
                true,
                (short) 1,
                LocalDate.now());
        var transacao = new Transacao(
                null,
                conta,
                BigDecimal.TEN,
                TipoTransacao.DEBITO,
                LocalDate.now());

        var contaService = Mockito.mock(ContaService.class);

        Mockito.when(contaService.atualizar(conta)).thenReturn(conta);
        var repo = Mockito.mock(TransacoesRepositoryImpl.class);
        doAnswer(t -> {
            transacao.setIdTransacao(1L);
            return null;
        }).when(repo).criar(transacao);
        Mockito.when(repo.consultaValorTransacionadoDia(1L)).thenReturn(BigDecimal.ZERO);

        var service = new TransacoesServiceImpl(repo, contaService);
        var result = service.criarTransacao(transacao);
        assertEquals(BigDecimal.ZERO, conta.getSaldo());
        assertEquals(1L, result.getIdTransacao());
    }

    @Test
    @DisplayName("Testa a criação de uma transação excedendo limite - saque")
    public void testaCriacaoSaqueExcedendoLimite() throws DomainException {
        var pessoa = Mockito.mock(Pessoa.class);
        var conta = new Conta(
                1L,
                pessoa,
                BigDecimal.valueOf(20),
                BigDecimal.TEN,
                true,
                (short) 1,
                LocalDate.now());
        var transacao = new Transacao(
                null,
                conta,
                BigDecimal.valueOf(20),
                TipoTransacao.DEBITO,
                LocalDate.now());

        var contaService = Mockito.mock(ContaService.class);

        Mockito.when(contaService.atualizar(conta)).thenReturn(conta);
        var repo = Mockito.mock(TransacoesRepositoryImpl.class);
        doAnswer(t -> {
            transacao.setIdTransacao(1L);
            return null;
        }).when(repo).criar(transacao);
        Mockito.when(repo.consultaValorTransacionadoDia(1L)).thenReturn(BigDecimal.ZERO);

        var service = new TransacoesServiceImpl(repo, contaService);
        assertThrows(LimiteDeSaqueDiarioExcedidoException.class, () -> service.criarTransacao(transacao));
    }

    @Test
    @DisplayName("Testa o cálculo do limite restante no dia - limite ok")
    public void testCalculoLimiteRestante() {
        var conta = Mockito.mock(Conta.class);
        Mockito.when(conta.getIdConta()).thenReturn(1L);
        Mockito.when(conta.getLimiteSaqueDiario()).thenReturn(BigDecimal.valueOf(20));

        var contaService = Mockito.mock(ContaService.class);

        var repo = Mockito.mock(TransacoesRepositoryImpl.class);
        Mockito.when(repo.consultaValorTransacionadoDia(1L)).thenReturn(BigDecimal.valueOf(15));

        var service = new TransacoesServiceImpl(repo, contaService);
        var result = service.calcularLimiteRestante(conta);

        assertEquals(BigDecimal.valueOf(5), result);
    }

    @Test
    @DisplayName("Testa o cálculo do limite restante no dia - sem limite")
    public void testCalculoSemLimiteRestante() {
        var conta = Mockito.mock(Conta.class);
        Mockito.when(conta.getIdConta()).thenReturn(1L);
        Mockito.when(conta.getLimiteSaqueDiario()).thenReturn(BigDecimal.valueOf(20));

        var contaService = Mockito.mock(ContaService.class);

        var repo = Mockito.mock(TransacoesRepositoryImpl.class);
        Mockito.when(repo.consultaValorTransacionadoDia(1L)).thenReturn(BigDecimal.valueOf(20));

        var service = new TransacoesServiceImpl(repo, contaService);
        var result = service.calcularLimiteRestante(conta);

        assertEquals(BigDecimal.ZERO, result);
    }


}
