package br.com.dock.conta.controllers;

import br.com.dock.conta.controllers.requests.ContaRequest;
import br.com.dock.conta.controllers.responses.BloqueioResponse;
import br.com.dock.conta.controllers.responses.ContaResponse;
import br.com.dock.conta.controllers.responses.ExtratoResponse;
import br.com.dock.conta.controllers.responses.SaldoResponse;
import br.com.dock.conta.core.entities.Conta;
import br.com.dock.conta.core.exceptions.ContaDuplicadaException;
import br.com.dock.conta.domain.services.ContaService;
import br.com.dock.conta.domain.services.PessoaService;
import br.com.dock.conta.domain.services.TransacoesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class ContasController extends BaseController {

    private ContaService contaService;
    private PessoaService pessoaService;
    private TransacoesService transacoesService;

    public ContasController(
            ContaService contaService,
            PessoaService pessoaService,
            TransacoesService transacoesService) {
        this.contaService = contaService;
        this.pessoaService = pessoaService;
        this.transacoesService = transacoesService;
    }

    @PostMapping
    public ResponseEntity<ContaResponse> criarConta(@Valid @RequestBody ContaRequest contaRequest) throws ContaDuplicadaException {
        var pessoa = this.pessoaService.buscarPorId(contaRequest.getIdPessoa());
        var conta = this.contaService.criarConta(
                new Conta(
                        pessoa,
                        BigDecimal.ZERO,
                        contaRequest.getLimiteSaqueDiario(),
                        true,
                        contaRequest.getTipoConta(),
                        LocalDate.now()));
        return ResponseEntity.ok(new ContaResponse(conta));
    }

    @GetMapping("/{idConta}/saldos")
    public ResponseEntity<SaldoResponse> consultarSaldo(@PathVariable Long idConta) {
        var conta = this.contaService.buscarConta(idConta);
        var saldo = this.contaService.consultarSaldo(idConta);
        var limiteDisponivel = this.transacoesService.calcularLimiteRestante(conta);
        return ResponseEntity.ok(new SaldoResponse(saldo, limiteDisponivel));
    }

    @PatchMapping("/{idConta}")
    public ResponseEntity<BloqueioResponse> bloquear(@PathVariable Long idConta) {
        var conta = this.contaService.buscarConta(idConta);
        this.contaService.bloquearConta(conta);
        return ResponseEntity.ok(new BloqueioResponse(conta.isFlagAtivo()));
    }

    @GetMapping("/{idConta}/extratos")
    public ResponseEntity<ExtratoResponse> consultarExtrato(
            @PathVariable Long idConta,
            @RequestParam("dataInicial") @DateTimeFormat(pattern = "dd-MM-yyyy") Optional<LocalDate> dataInicial,
            @RequestParam("dataFinal") @DateTimeFormat(pattern = "dd-MM-yyyy") Optional<LocalDate> dataFinal) {
        var conta = this.contaService.buscarConta(idConta);
        var saldo = this.contaService.consultarSaldo(idConta);
        var limiteDisponivel = this.transacoesService.calcularLimiteRestante(conta);
        var listaTransacoes = this.transacoesService.listarTransacoesPorPeriodo(idConta, dataInicial, dataFinal);
        return ResponseEntity.ok(new ExtratoResponse(saldo, limiteDisponivel, listaTransacoes));
    }
}
