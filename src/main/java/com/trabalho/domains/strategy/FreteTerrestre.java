package com.trabalho.domains.strategy;

import java.math.BigDecimal;

public class FreteTerrestre implements FreteStrategy {

    @Override
    public BigDecimal calcularFrete(BigDecimal valorPedido) {
        return valorPedido.multiply(new BigDecimal("0.05")); // 5%
    }

    @Override
    public String getDescricao() {
        return "Frete Terrestre - 5% do valor do pedido";
    }
}