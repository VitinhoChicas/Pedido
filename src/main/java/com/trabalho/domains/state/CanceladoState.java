package com.trabalho.domains.state;
import com.trabalho.domains.EstadoPedido;

public class CanceladoState implements State {

    private EstadoPedido estadoPedido;

    public CanceladoState(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    @Override
    public void sucessoAoPagar() {
        throw new IllegalStateException("Operação não suportada - pedido cancelado");
    }

    @Override
    public void cancelarPedido() {
        throw new IllegalStateException("Operação não suportada - pedido já está cancelado");
    }

    @Override
    public void despacharPedido() {
        throw new IllegalStateException("Operação não suportada - pedido cancelado");
    }
}