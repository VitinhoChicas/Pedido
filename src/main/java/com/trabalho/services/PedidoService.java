package com.trabalho.services;

import com.trabalho.domains.*;
import com.trabalho.domains.dtos.PedidoDTO;
import com.trabalho.domains.enums.StatusPedido;
import com.trabalho.domains.strategy.FreteStrategy;
import com.trabalho.domains.strategy.FreteStrategyFactory;
import com.trabalho.repositories.PedidoRepository;
import com.trabalho.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public List<PedidoDTO> findAll(){
        return repository.findAll().stream()
                .map(obj -> new PedidoDTO(obj))
                .collect(Collectors.toList());
    }

    public Pedido findbyId(Long id){
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado! Id: " + id));
    }

    // MÉTODO PRINCIPAL PARA CRIAR PEDIDO (com cálculo de frete)
    public PedidoDTO criarPedido(PedidoDTO dto) {
        dto.setId(null); // Garante que é um novo pedido
        Pedido pedido = dto.toEntity();

        // Usa Strategy Pattern para calcular frete
        calcularFreteComStrategy(pedido);

        Pedido salvo = repository.save(pedido);
        return new PedidoDTO(salvo);
    }

    public Pedido update(Long id, PedidoDTO objDto){
        objDto.setId(id);
        Pedido oldObj = findbyId(id);
        oldObj = new Pedido(objDto);

        // Recalcula frete se necessário
        calcularFreteComStrategy(oldObj);

        return repository.save(oldObj);
    }

    public void delete(Long id){
        Pedido obj = findbyId(id);
        repository.deleteById(id);
    }

    // MÉTODOS DE TRANSIÇÃO DE ESTADO
    public PedidoDTO pagarPedido(Long id) {
        Pedido pedido = getPedido(id);

        try {
            pedido.pagar();
            return new PedidoDTO(repository.save(pedido));
        } catch (IllegalStateException e) {
            throw new RuntimeException("Erro ao pagar pedido: " + e.getMessage());
        }
    }

    public PedidoDTO cancelarPedido(Long id) {
        Pedido pedido = getPedido(id);

        try {
            pedido.cancelar();
            return new PedidoDTO(repository.save(pedido));
        } catch (IllegalStateException e) {
            throw new RuntimeException("Erro ao cancelar pedido: " + e.getMessage());
        }
    }

    public PedidoDTO enviarPedido(Long id) {
        Pedido pedido = getPedido(id);

        try {
            pedido.enviar();
            return new PedidoDTO(repository.save(pedido));
        } catch (IllegalStateException e) {
            throw new RuntimeException("Erro ao enviar pedido: " + e.getMessage());
        }
    }

    // MÉTODOS PRIVADOS
    private void calcularFreteComStrategy(Pedido pedido) {
        FreteStrategy strategy = FreteStrategyFactory.getStrategy(pedido.getTipoFrete());
        pedido.setValorFrete(strategy.calcularFrete(pedido.getValor()));

        System.out.println("Calculando frete: " + strategy.getDescricao());
        System.out.println("Valor do frete: R$ " + pedido.getValorFrete());
    }

    private Pedido getPedido(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + id));
    }
}