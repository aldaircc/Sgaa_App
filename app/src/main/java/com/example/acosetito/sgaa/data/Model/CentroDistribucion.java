package com.example.acosetito.sgaa.data.Model;

import com.google.gson.annotations.SerializedName;

public class CentroDistribucion {
    @SerializedName("Id_Centro")
    private Integer Id_Centro;
    @SerializedName("Centro")
    private String Centro;

    public Integer getId_Centro() {
        return Id_Centro;
    }

    public void setId_Centro(Integer id_Centro) {
        Id_Centro = id_Centro;
    }

    public String getCentro() {
        return Centro;
    }

    public void setCentro(String centro) {
        Centro = centro;
    }
}
