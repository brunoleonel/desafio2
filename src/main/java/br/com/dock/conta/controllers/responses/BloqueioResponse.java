package br.com.dock.conta.controllers.responses;

public class BloqueioResponse {

    private boolean ativo;

    public BloqueioResponse(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
