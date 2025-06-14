package com.trabalho.domains.enums;


public enum StatusPedido {

    AGUARDANDO_PAGAMENTO(0, "Aguardando Pagamento"),
    PAGO(1, "Pago"),
    ENVIADO(2, "Enviado"),
    CANCELADO(3, "Cancelado");

    private Integer id;
    private String descricao;

    StatusPedido(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static StatusPedido toEnum(Integer id) {
        if (id == null) return null;
        for (StatusPedido x : StatusPedido.values()) {
            if (id.equals(x.getId())) return x;
        }
        throw new IllegalArgumentException("StatusPedido inválido: " + id);
    }
}
