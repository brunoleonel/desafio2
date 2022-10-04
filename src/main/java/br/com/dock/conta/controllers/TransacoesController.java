package br.com.dock.conta.controllers;

import br.com.dock.conta.controllers.requests.TransacaoRequest;
import br.com.dock.conta.controllers.responses.TransacaoResponse;
import br.com.dock.conta.core.entities.TipoTransacao;
import br.com.dock.conta.core.entities.Transacao;
import br.com.dock.conta.core.exceptions.DomainException;
import br.com.dock.conta.domain.services.ContaService;
import br.com.dock.conta.domain.services.TransacoesService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController extends BaseController {

    private TransacoesService transacoesService;
    private ContaService contaService;

    public TransacoesController(TransacoesService transacoesService, ContaService contaService) {
        this.transacoesService = transacoesService;
        this.contaService = contaService;
    }

    @PostMapping("/{idConta}/depositos")
    public TransacaoResponse depositar(
            @PathVariable Long idConta,
            @Valid @RequestBody TransacaoRequest transacaoRequest) throws DomainException {
        var conta = this.contaService.buscarConta(idConta);
        var transacao = new Transacao(//new Deposito(
                null,
                conta,
                transacaoRequest.getValor(),
                TipoTransacao.CREDITO,
                LocalDate.now());
        this.transacoesService.criaTransacao(transacao);
        return new TransacaoResponse(transacao);
    }

    @PostMapping("/{idConta}/saques")
    public TransacaoResponse sacar(
            @PathVariable Long idConta,
            @Valid @RequestBody TransacaoRequest transacaoRequest) throws DomainException {
        var conta = this.contaService.buscarConta(idConta);
        var transacao = new Transacao(//Saque(
                null,
                conta,
                transacaoRequest.getValor(),
                TipoTransacao.DEBITO,
                LocalDate.now());
        this.transacoesService.criaTransacao(transacao);
        return new TransacaoResponse(transacao);
    }
}
