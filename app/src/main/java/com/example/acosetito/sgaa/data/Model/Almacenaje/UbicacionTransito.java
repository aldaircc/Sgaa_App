package com.example.acosetito.sgaa.data.Model.Almacenaje;

import com.google.gson.annotations.SerializedName;

public class UbicacionTransito {
    @SerializedName("almacen")
    public String almacen;
    @SerializedName("sector")
    public String sector;
    @SerializedName("pasillo")
    public String pasillo;
    @SerializedName("Id_Ubicacion")
    public Integer Id_Ubicacion;
    @SerializedName("ubicacion")
    public String ubicacion;
    @SerializedName("total")
    public Integer total;

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getPasillo() {
        return pasillo;
    }

    public void setPasillo(String pasillo) {
        this.pasillo = pasillo;
    }

    public Integer getId_Ubicacion() {
        return Id_Ubicacion;
    }

    public void setId_Ubicacion(Integer id_Ubicacion) {
        Id_Ubicacion = id_Ubicacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
