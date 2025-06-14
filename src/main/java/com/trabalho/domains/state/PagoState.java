package com.trabalho.domains.state;

import com.trabalho.domains.EstadoPedido;

public class PagoState implements State {

    private EstadoPedido estadoPedido;

    public PagoState(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    @Override
    public void sucessoAoPagar() {
        throw new IllegalStateException("Operação não suportada - pedido já foi pago");
    }

    @Override
    public void cancelarPedido() {
        // CORREÇÃO: estava mudando para enviado, agora muda para cancelado
        this.estadoPedido.setEstadoAtual(estadoPedido.getCancelado());
    }

    @Override
    public void despacharPedido() {
        this.estadoPedido.setEstadoAtual(estadoPedido.getEnviado());
    }
}