package com.trabalho.domains.state;
import com.trabalho.domains.EstadoPedido;

public class AguardandoPagamentoState implements State {

    private EstadoPedido estadoPedido;

    public AguardandoPagamentoState(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    @Override
    public void sucessoAoPagar() {
        this.estadoPedido.setEstadoAtual(estadoPedido.getPago());
    }

    @Override
    public void cancelarPedido() {
        this.estadoPedido.setEstadoAtual(estadoPedido.getCancelado());
    }

    @Override
    public void despacharPedido() {
        throw new IllegalStateException("Operação não suportada - pedido ainda não foi pago");
    }
}