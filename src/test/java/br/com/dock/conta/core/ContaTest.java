package br.com.dock.conta.core;

import br.com.dock.conta.core.entities.Conta;
import br.com.dock.conta.core.entities.Pessoa;
import br.com.dock.conta.core.exceptions.ContaInativaException;
import br.com.dock.conta.core.exceptions.LimiteDeSaqueDiarioExcedidoException;
import br.com.dock.conta.core.exceptions.SaldoInsuficienteException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaTest {

    @Test
    @DisplayName("Testa validação de status ativo")
    public void testaValidacaoStatusAtivo() {
        var pessoa = Mockito.mock(Pessoa.class);
        var conta = new Conta(
                pessoa,
                BigDecimal.ZERO,
                BigDecimal.TEN,
                true,
                (short) 1,
                LocalDate.now());

        assertDoesNotThrow(() -> conta.validarStatus());
    }

    @Test
    @DisplayName("Testa validação de status inativo")
    public void testaValidacaoStatusInativo() {
        var pessoa = Mockito.mock(Pessoa.class);
        var conta = new Conta(
                pessoa,
                BigDecimal.ZERO,
                BigDecimal.TEN,
                false,
                (short) 1,
                LocalDate.now());

        assertThrows(ContaInativaException.class, () -> conta.validarStatus());
    }

    @Test
    @DisplayName("Testa validação de limite de saque diário - falha")
    public void testaValidacaoLimiteSaqueFalha() {
        var pessoa = Mockito.mock(Pessoa.class);
        var conta = new Conta(
                pessoa,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                true,
                (short) 1,
                LocalDate.now());

        assertThrows(LimiteDeSaqueDiarioExcedidoException.class, () -> conta.validarLimiteSaqueDiario(BigDecimal.TEN));
    }

    @Test
    @DisplayName("Testa validação de limite de saque diário - sucesso")
    public void testaValidacaoLimiteSaqueSucesso() {
        var pessoa = Mockito.mock(Pessoa.class);
        var conta = new Conta(
                pessoa,
                BigDecimal.ZERO,
                BigDecimal.TEN,
                true,
                (short) 1,
                LocalDate.now());

        assertDoesNotThrow(() -> conta.validarLimiteSaqueDiario(BigDecimal.TEN));
    }

    @Test
    @DisplayName("Testa validação de saldo - falha")
    public void testaValidacaoSaldoFalha() {
        var pessoa = Mockito.mock(Pessoa.class);
        var conta = new Conta(
                pessoa,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                true,
                (short) 1,
                LocalDate.now());

        assertThrows(SaldoInsuficienteException.class, () -> conta.validarSaldo(BigDecimal.TEN));
    }

    @Test
    @DisplayName("Testa validação de saldo - sucesso")
    public void testaValidacaoSaldoSucesso() {
        var pessoa = Mockito.mock(Pessoa.class);
        var conta = new Conta(
                pessoa,
                BigDecimal.TEN,
                BigDecimal.TEN,
                true,
                (short) 1,
                LocalDate.now());

        assertDoesNotThrow(() -> conta.validarSaldo(BigDecimal.TEN));
    }

    @Test
    @DisplayName("Testa operação de crédito")
    public void testaCredito() {
        var pessoa = Mockito.mock(Pessoa.class);
        var conta = new Conta(
                pessoa,
                BigDecimal.ZERO,
                BigDecimal.TEN,
                true,
                (short) 1,
                LocalDate.now());

        conta.creditar(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, conta.getSaldo());
    }

    @Test
    @DisplayName("Testa operação de débito")
    public void testaDedito() {
        var pessoa = Mockito.mock(Pessoa.class);
        var conta = new Conta(
                pessoa,
                BigDecimal.TEN,
                BigDecimal.TEN,
                true,
                (short) 1,
                LocalDate.now());

        conta.debitar(BigDecimal.TEN);
        assertEquals(BigDecimal.ZERO, conta.getSaldo());
    }
}
