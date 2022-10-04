package br.com.dock.conta.controllers;

import br.com.dock.conta.controllers.requests.ContaRequest;
import br.com.dock.conta.controllers.responses.ContaResponse;
import br.com.dock.conta.core.entities.Conta;
import br.com.dock.conta.core.exceptions.ContaDuplicadaException;
import br.com.dock.conta.domain.services.ContaService;
import br.com.dock.conta.domain.services.PessoaService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/contas")
public class ContasController extends BaseController {

    private ContaService contaService;
    private PessoaService pessoaService;

    public ContasController(ContaService contaService, PessoaService pessoaService) {
        this.contaService = contaService;
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ContaResponse criarConta(@Valid @RequestBody ContaRequest contaRequest) throws ContaDuplicadaException {
        var pessoa = this.pessoaService.buscarPorId(contaRequest.getIdPessoa());
        var conta = this.contaService.criarConta(
                new Conta(
                    pessoa,
                    BigDecimal.ZERO,
                    contaRequest.getLimiteSaqueDiario(),
                    true,
                    contaRequest.getTipoConta(),
                    LocalDate.now()));
        return new ContaResponse(conta);
    }

    @GetMapping("/{idConta}/saldo")
    public String consultarSaldo(@PathVariable("idConta") Long idConta) {
        return String.valueOf(idConta);
    }

    @PatchMapping("/{idConta}/bloqueio")
    public ContaResponse bloquear(@PathVariable("idConta") Long idConta) {
        var conta = this.contaService.buscarConta(idConta);
        this.contaService.bloquearConta(conta);
        return new ContaResponse(conta);
    }
}
