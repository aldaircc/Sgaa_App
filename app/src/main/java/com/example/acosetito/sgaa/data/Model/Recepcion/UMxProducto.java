package com.example.acosetito.sgaa.data.Model.Recepcion;

import com.google.gson.annotations.SerializedName;

public class UMxProducto {
    @SerializedName("Id_UM")
    private Integer Id_UM;
    @SerializedName("Nombre")
    private String Nombre;
    @SerializedName("Alias")
    private String Alias;
    @SerializedName("Factor")
    private Double Factor;
    @SerializedName("CantXBulto")
    private Double CantXBulto;
    @SerializedName("CantBulto")
    private Double CantBulto;
    @SerializedName("EAN14")
    private String EAN14;

    public Integer getId_UM() {
        return Id_UM;
    }

    public void setId_UM(Integer id_UM) {
        Id_UM = id_UM;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public Double getFactor() {
        return Factor;
    }

    public void setFactor(Double factor) {
        Factor = factor;
    }

    public Double getCantXBulto() {
        return CantXBulto;
    }

    public void setCantXBulto(Double cantXBulto) {
        CantXBulto = cantXBulto;
    }

    public Double getCantBulto() {
        return CantBulto;
    }

    public void setCantBulto(Double cantBulto) {
        CantBulto = cantBulto;
    }

    public String getEAN14() {
        return EAN14;
    }

    public void setEAN14(String EAN14) {
        this.EAN14 = EAN14;
    }
}
