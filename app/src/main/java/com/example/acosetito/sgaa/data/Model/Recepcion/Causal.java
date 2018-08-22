package com.example.acosetito.sgaa.data.Model.Recepcion;

import com.google.gson.annotations.SerializedName;

public class Causal {
    @SerializedName("Id_Causal")
    private Integer Id_Causal;
    @SerializedName("Causal")
    private String Causal;

    public Integer getId_Causal() {
        return Id_Causal;
    }

    public void setId_Causal(Integer id_Causal) {
        Id_Causal = id_Causal;
    }

    public String getCausal() {
        return Causal;
    }

    public void setCausal(String causal) {
        Causal = causal;
    }
}
