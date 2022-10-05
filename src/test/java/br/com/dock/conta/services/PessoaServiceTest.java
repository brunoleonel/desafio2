package br.com.dock.conta.services;

import br.com.dock.conta.core.entities.Pessoa;
import br.com.dock.conta.domain.repositories.PessoasRepository;
import br.com.dock.conta.repositories.impl.PessoasRepositoryImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class PessoaServiceTest {

    @Test
    @DisplayName("Testa consulta de pessoa")
    public void testaConsultaPessoa() {
        var pessoa = new Pessoa(
                1L,
                "José Antonio",
                "00000000001",
                LocalDate.now());
        var repo = Mockito.mock(PessoasRepositoryImpl.class);
        Mockito.when(repo.buscarPorId(1L)).thenReturn(pessoa);
        var result = repo.buscarPorId(1L);
        assertEquals(pessoa, result);
    }
}
