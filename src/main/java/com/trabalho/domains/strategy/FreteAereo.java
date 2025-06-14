package com.trabalho.domains.strategy;

import java.math.BigDecimal;

public class FreteAereo implements FreteStrategy {

    @Override
    public BigDecimal calcularFrete(BigDecimal valorPedido) {
        return valorPedido.multiply(new BigDecimal("0.10")); // 10%
    }

    @Override
    public String getDescricao() {
        return "Frete Aéreo - 10% do valor do pedido";
    }
}