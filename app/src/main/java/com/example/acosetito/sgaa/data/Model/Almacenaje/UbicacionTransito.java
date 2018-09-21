package com.example.acosetito.sgaa.data.Model.Almacenaje;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class UbicacionTransito implements Comparable, Cloneable{
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

    @Override
    public int compareTo(@NonNull Object o) {
        UbicacionTransito compare = (UbicacionTransito) o;
        if (compare.Id_Ubicacion.equals(this.Id_Ubicacion) && compare.almacen.equals(this.almacen)
                && compare.sector.equals(this.sector) && compare.pasillo.equals(this.pasillo)
                && compare.ubicacion.equals(this.ubicacion)){
            return 0;
        }
        return 1;
    }

    @Override
    public UbicacionTransito clone(){
        UbicacionTransito clone;
        try {
            clone = (UbicacionTransito)super.clone();
        }catch (CloneNotSupportedException ex){
            throw  new RuntimeException(ex);
        }
        return clone;
    }
}
