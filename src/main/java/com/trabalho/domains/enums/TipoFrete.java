package com.trabalho.domains.enums;


public enum TipoFrete {

    TERRESTRE(0, "Terrestre"),
    AEREO(1, "Aéreo");

    private Integer id;
    private String descricao;

    TipoFrete(Integer id, String descricao) {
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

    public static TipoFrete toEnum(Integer id) {
        if (id == null) return null;
        for (TipoFrete x : TipoFrete.values()) {
            if (id.equals(x.getId())) return x;
        }
        throw new IllegalArgumentException("TipoFrete inválido: " + id);
    }
}
