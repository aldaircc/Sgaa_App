package com.example.acosetito.sgaa.data.Model.Impresora;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaEtiqueta {

    @SerializedName("etiqueta")
    private List<Etiqueta> etiqueta;

    public List<Etiqueta> getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(List<Etiqueta> etiqueta) {
        this.etiqueta = etiqueta;
    }
}
