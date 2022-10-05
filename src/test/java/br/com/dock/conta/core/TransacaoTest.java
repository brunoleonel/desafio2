package br.com.dock.conta.core;

import br.com.dock.conta.core.entities.Conta;
import br.com.dock.conta.core.entities.TipoTransacao;
import br.com.dock.conta.core.entities.Transacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoTest {

    @Test
    @DisplayName("Testa execução das operações - credito")
    public void testaOperacaoCredito() {
        var conta = Mockito.mock(Conta.class);
        Mockito.when(conta.getSaldo()).thenReturn(BigDecimal.TEN);
        var transacao = new Transacao(
                1L,
                conta,
                BigDecimal.TEN, TipoTransacao.CREDITO,
                LocalDate.now());
        Assertions.assertEquals(BigDecimal.TEN, conta.getSaldo());
    }
}
