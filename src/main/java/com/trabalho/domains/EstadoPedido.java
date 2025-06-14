package com.trabalho.domains;

import com.trabalho.domains.state.*;
import com.trabalho.domains.enums.StatusPedido;

public class EstadoPedido {
    private State aguardandoPagamento;
    private State pago;
    private State cancelado;
    private State enviado;

    // Estado atual do pedido
    private State estadoAtual;

    // Referência ao pedido para atualizar status
    private Pedido pedido;

    public EstadoPedido(Pedido pedido) {
        this.pedido = pedido;
        this.aguardandoPagamento = new AguardandoPagamentoState(this);
        this.pago = new PagoState(this);
        this.cancelado = new CanceladoState(this);
        this.enviado = new EnviadoState(this);

        // Define estado inicial baseado no status atual do pedido
        setEstadoByStatus(pedido.getStatus());
    }

    private void setEstadoByStatus(StatusPedido status) {
        switch (status) {
            case AGUARDANDO_PAGAMENTO:
                this.estadoAtual = this.aguardandoPagamento;
                break;
            case PAGO:
                this.estadoAtual = this.pago;
                break;
            case ENVIADO:
                this.estadoAtual = this.enviado;
                break;
            case CANCELADO:
                this.estadoAtual = this.cancelado;
                break;
        }
    }

    public void sucessoAoPagar() {
        System.out.println("Processando pagamento...");
        this.estadoAtual.sucessoAoPagar();
        this.pedido.setStatus(StatusPedido.PAGO);
        System.out.println("Pedido pago com sucesso!");
    }

    public void cancelarPedido() {
        System.out.println("Cancelando pedido...");
        this.estadoAtual.cancelarPedido();
        this.pedido.setStatus(StatusPedido.CANCELADO);
        System.out.println("Pedido cancelado!");
    }

    public void despacharPedido() {
        System.out.println("Despachando pedido...");
        this.estadoAtual.despacharPedido();
        this.pedido.setStatus(StatusPedido.ENVIADO);
        System.out.println("Pedido enviado!");
    }

    // Getters
    public State getAguardandoPagamento() {
        return aguardandoPagamento;
    }

    public State getCancelado() {
        return cancelado;
    }

    public State getEnviado() {
        return enviado;
    }

    public State getPago() {
        return pago;
    }

    public State getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(State estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    public Pedido getPedido() {
        return pedido;
    }
}