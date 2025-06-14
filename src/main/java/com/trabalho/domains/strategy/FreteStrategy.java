package com.trabalho.domains.strategy;

import java.math.BigDecimal;

public interface FreteStrategy {
    BigDecimal calcularFrete(BigDecimal valorPedido);
    String getDescricao();
}