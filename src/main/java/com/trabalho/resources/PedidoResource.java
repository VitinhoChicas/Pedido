package com.trabalho.resources;

import com.trabalho.domains.Pedido;
import com.trabalho.domains.dtos.PedidoDTO;
import com.trabalho.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Long id){
        Pedido obj = this.service.findbyId(id);
        return ResponseEntity.ok().body(new PedidoDTO(obj));
    }

    // MANTÉM APENAS ESTE MÉTODO POST (o mais completo)
    @PostMapping
    public ResponseEntity<PedidoDTO> create(@Valid @RequestBody PedidoDTO dto) {
        PedidoDTO pedidoCriado = service.criarPedido(dto); // Usa o método mais completo

        // Cria o URI para o recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pedidoCriado.getId()).toUri();

        // Retorna a resposta com o status 201 Created e o DTO criado
        return ResponseEntity.created(uri).body(pedidoCriado);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable Long id, @Valid @RequestBody PedidoDTO objDto){
        Pedido obj = service.update(id, objDto);
        return ResponseEntity.ok().body(new PedidoDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // MÉTODOS DE TRANSIÇÃO DE ESTADO
    @PostMapping("/{id}/pagar")
    public ResponseEntity<PedidoDTO> pagar(@PathVariable Long id) {
        PedidoDTO pedido = service.pagarPedido(id);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<PedidoDTO> cancelar(@PathVariable Long id) {
        PedidoDTO pedido = service.cancelarPedido(id);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/{id}/enviar")
    public ResponseEntity<PedidoDTO> enviar(@PathVariable Long id) {
        PedidoDTO pedido = service.enviarPedido(id);
        return ResponseEntity.ok(pedido);
    }
}