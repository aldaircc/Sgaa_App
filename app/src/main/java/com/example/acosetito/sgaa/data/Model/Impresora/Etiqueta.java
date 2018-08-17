package com.example.acosetito.sgaa.data.Model.Impresora;

import com.google.gson.annotations.SerializedName;

public class Etiqueta {
    @SerializedName("campo")
    private String campo;
    @SerializedName("valor")
    private String valor;

    public Etiqueta(String campo, String valor) {
        this.campo = campo;
        this.valor = valor;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
