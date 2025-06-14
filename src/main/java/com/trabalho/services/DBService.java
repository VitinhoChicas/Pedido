package com.trabalho.services;

import com.trabalho.domains.Pedido;
import com.trabalho.domains.enums.StatusPedido;
import com.trabalho.domains.enums.TipoFrete;
import com.trabalho.domains.strategy.FreteStrategy;
import com.trabalho.domains.strategy.FreteStrategyFactory;
import com.trabalho.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DBService {

    @Autowired
    private PedidoRepository pedidoRepo;

    public void initDB(){

        Pedido pedido01 = new Pedido();
        pedido01.setValor(new BigDecimal("100"));
        pedido01.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        pedido01.setTipoFrete(TipoFrete.AEREO);


        FreteStrategy freteAereo = FreteStrategyFactory.getStrategy(TipoFrete.AEREO);
        pedido01.setValorFrete(freteAereo.calcularFrete(pedido01.getValor()));

        pedidoRepo.save(pedido01);


        Pedido pedido02 = new Pedido();
        pedido02.setValor(new BigDecimal("200"));
        pedido02.setStatus(StatusPedido.PAGO);
        pedido02.setTipoFrete(TipoFrete.TERRESTRE);


        FreteStrategy freteTerrestre = FreteStrategyFactory.getStrategy(TipoFrete.TERRESTRE);
        pedido02.setValorFrete(freteTerrestre.calcularFrete(pedido02.getValor()));

        pedidoRepo.save(pedido02);


        Pedido pedido03 = new Pedido();
        pedido03.setValor(new BigDecimal("50"));
        pedido03.setStatus(StatusPedido.ENVIADO);
        pedido03.setTipoFrete(TipoFrete.AEREO);

        FreteStrategy freteAereo2 = FreteStrategyFactory.getStrategy(TipoFrete.AEREO);
        pedido03.setValorFrete(freteAereo2.calcularFrete(pedido03.getValor()));

        pedidoRepo.save(pedido03);


        System.out.println("=== DADOS DE TESTE CRIADOS ===");
        System.out.println("Pedido 1: R$ 100,00 + Frete Aéreo R$ " + pedido01.getValorFrete() + " = Total: R$ " + pedido01.getValor().add(pedido01.getValorFrete()));
        System.out.println("Pedido 2: R$ 200,00 + Frete Terrestre R$ " + pedido02.getValorFrete() + " = Total: R$ " + pedido02.getValor().add(pedido02.getValorFrete()));
        System.out.println("Pedido 3: R$ 50,00 + Frete Aéreo R$ " + pedido03.getValorFrete() + " = Total: R$ " + pedido03.getValor().add(pedido03.getValorFrete()));
    }
}