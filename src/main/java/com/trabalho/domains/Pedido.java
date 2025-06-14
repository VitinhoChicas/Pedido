package com.trabalho.domains;

import com.trabalho.domains.dtos.PedidoDTO;
import com.trabalho.domains.enums.StatusPedido;
import com.trabalho.domains.enums.TipoFrete;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Digits(integer = 15, fraction = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private StatusPedido status;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_frete")
    private TipoFrete tipoFrete;

    @NotNull
    @Digits(integer = 18, fraction = 2)
    private BigDecimal valorFrete;

    // Pattern State - não persistido no banco
    @Transient
    private EstadoPedido estadoPedido;

    public Pedido() {
        this.status = StatusPedido.AGUARDANDO_PAGAMENTO;
        initEstadoPedido();
    }

    public Pedido(Long id, BigDecimal valor, StatusPedido status, TipoFrete tipoFrete, BigDecimal valorFrete) {
        this.id = id;
        this.valor = valor;
        this.status = status;
        this.tipoFrete = tipoFrete;
        this.valorFrete = valorFrete;
        initEstadoPedido();
    }

    public Pedido(PedidoDTO dto) {
        this.id = dto.getId();
        this.valor = dto.getValor();
        this.status = StatusPedido.toEnum(dto.getStatus());
        this.tipoFrete = TipoFrete.toEnum(dto.getTipoFrete());
        this.valorFrete = dto.getValorFrete();
        initEstadoPedido();
    }

    @PostLoad
    private void initEstadoPedido() {
        if (this.estadoPedido == null) {
            this.estadoPedido = new EstadoPedido(this);
        }
    }

    // Métodos que usam o Pattern State
    public void pagar() {
        this.estadoPedido.sucessoAoPagar();
    }

    public void cancelar() {
        this.estadoPedido.cancelarPedido();
    }

    public void enviar() {
        this.estadoPedido.despacharPedido();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public TipoFrete getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(TipoFrete tipoFrete) {
        this.tipoFrete = tipoFrete;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public EstadoPedido getEstadoPedido() {
        if (this.estadoPedido == null) {
            initEstadoPedido();
        }
        return estadoPedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) && Objects.equals(valor, pedido.valor) &&
                status == pedido.status && tipoFrete == pedido.tipoFrete && Objects.equals(valorFrete, pedido.valorFrete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, status, tipoFrete, valorFrete);
    }
}