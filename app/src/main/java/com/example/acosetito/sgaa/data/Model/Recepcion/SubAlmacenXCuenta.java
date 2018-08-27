package com.example.acosetito.sgaa.data.Model.Recepcion;
import com.google.gson.annotations.SerializedName;

public class SubAlmacenXCuenta {
    @SerializedName("Id_SubAlmacen")
    private Integer Id_SubAlmacen;
    @SerializedName("NombreSubAlmacen")
    private String NombreSubAlmacen;
    @SerializedName("CodigoERP")
    private String CodigoERP;

    public Integer getId_SubAlmacen() {
        return Id_SubAlmacen;
    }

    public void setId_SubAlmacen(Integer id_SubAlmacen) {
        Id_SubAlmacen = id_SubAlmacen;
    }

    public String getNombreSubAlmacen() {
        return NombreSubAlmacen;
    }

    public void setNombreSubAlmacen(String nombreSubAlmacen) {
        NombreSubAlmacen = nombreSubAlmacen;
    }

    public String getCodigoERP() {
        return CodigoERP;
    }

    public void setCodigoERP(String codigoERP) {
        CodigoERP = codigoERP;
    }
}
