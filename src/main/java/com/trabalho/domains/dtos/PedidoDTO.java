package com.trabalho.domains.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.trabalho.domains.Pedido;
import com.trabalho.domains.enums.StatusPedido;
import com.trabalho.domains.enums.TipoFrete;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PedidoDTO {

    private Long id;

    @NotNull(message = "O campo valor não pode estar vazio")
    @Digits(integer = 15, fraction = 2)
    private BigDecimal valor;

    @NotNull(message = "O campo status não pode estar vazio")
    private int status;

    @NotNull(message = "O campo tipoFrete não pode estar vazio")
    private int tipoFrete;

    @NotNull(message = "O campo valorFrete não pode estar vazio")
    @Digits(integer = 15, fraction = 2)
    private BigDecimal valorFrete;

    // Campos adicionais para melhor visualização
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String statusDescricao;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tipoFreteDescricao;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal valorTotal;

    public PedidoDTO() {
    }

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.valor = pedido.getValor();
        this.status = pedido.getStatus().getId();
        this.tipoFrete = pedido.getTipoFrete().getId();
        this.valorFrete = pedido.getValorFrete();

        // Campos adicionais para melhor visualização
        this.statusDescricao = pedido.getStatus().getDescricao();
        this.tipoFreteDescricao = pedido.getTipoFrete().getDescricao();
        this.valorTotal = pedido.getValor().add(pedido.getValorFrete());
    }

    public Pedido toEntity() {
        Pedido pedido = new Pedido();
        pedido.setId(this.id);
        pedido.setValor(this.valor);

        // Converte o int status para o enum StatusPedido
        pedido.setStatus(StatusPedido.toEnum(this.status));

        // Converte o int tipoFrete para o enum TipoFrete
        pedido.setTipoFrete(TipoFrete.toEnum(this.tipoFrete));

        pedido.setValorFrete(this.valorFrete);

        return pedido;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(int tipoFrete) {
        this.tipoFrete = tipoFrete;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public String getStatusDescricao() {
        return statusDescricao;
    }

    public void setStatusDescricao(String statusDescricao) {
        this.statusDescricao = statusDescricao;
    }

    public String getTipoFreteDescricao() {
        return tipoFreteDescricao;
    }

    public void setTipoFreteDescricao(String tipoFreteDescricao) {
        this.tipoFreteDescricao = tipoFreteDescricao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}