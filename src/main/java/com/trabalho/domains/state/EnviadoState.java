package com.trabalho.domains.state;
import com.trabalho.domains.EstadoPedido;

public class EnviadoState implements State {

    private EstadoPedido estadoPedido;

    public EnviadoState(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    @Override
    public void sucessoAoPagar() {
        throw new IllegalStateException("Operação não suportada - pedido já enviado");
    }

    @Override
    public void cancelarPedido() {
        throw new IllegalStateException("Operação não suportada - pedido já enviado, não pode ser cancelado");
    }

    @Override
    public void despacharPedido() {
        throw new IllegalStateException("Operação não suportada - pedido já foi enviado");
    }
}