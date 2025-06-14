package com.trabalho.domains.strategy;

import com.trabalho.domains.enums.TipoFrete;

public class FreteStrategyFactory {

    public static FreteStrategy getStrategy(TipoFrete tipoFrete) {
        switch (tipoFrete) {
            case TERRESTRE:
                return new FreteTerrestre();
            case AEREO:
                return new FreteAereo();
            default:
                throw new IllegalArgumentException("Tipo de frete não suportado: " + tipoFrete);
        }
    }
}