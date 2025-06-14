package com.trabalho.domains.state;

public interface State {
    public void sucessoAoPagar();
    public void cancelarPedido();
    public void despacharPedido();
}
