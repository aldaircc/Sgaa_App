package com.example.acosetito.sgaa.data.Model.Almacenaje;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UATransito {
    @SerializedName("UA_CodBarra")
    public String UA_CodBarra;
    @SerializedName("Id_Producto")
    public Integer Id_Producto;
    @SerializedName("CodigoProducto")
    public String CodigoProducto;
    @SerializedName("NombreProducto")
    public String NombreProducto;
    @SerializedName("Id_UM")
    public Integer Id_UM;
    @SerializedName("UM")
    public String UM;
    @SerializedName("Cantidad")
    public Double Cantidad;
    @SerializedName("LoteLab")
    public String LoteLab;
    @SerializedName("LotePT")
    public String LotePT;
    @SerializedName("FlagDisponible")
    public Boolean FlagDisponible;
    @SerializedName("FlagAveriado")
    public Boolean FlagAveriado;
    @SerializedName("Id_Marca")
    public Integer Id_Marca;
    @SerializedName("FlagCuarentena")
    public Boolean FlagCuarentena;
    @SerializedName("Id_Condicion")
    public Integer Id_Condicion;
    @SerializedName("FechaEmision")
    public Date FechaEmision;
    @SerializedName("FechaVencimiento")
    public Date FechaVencimiento;
    @SerializedName("Serie")
    public String Serie;

    public String getUA_CodBarra() {
        return UA_CodBarra;
    }

    public void setUA_CodBarra(String UA_CodBarra) {
        this.UA_CodBarra = UA_CodBarra;
    }

    public Integer getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(Integer id_Producto) {
        Id_Producto = id_Producto;
    }

    public String getCodigoProducto() {
        return CodigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        CodigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }

    public Integer getId_UM() {
        return Id_UM;
    }

    public void setId_UM(Integer id_UM) {
        Id_UM = id_UM;
    }

    public String getUM() {
        return UM;
    }

    public void setUM(String UM) {
        this.UM = UM;
    }

    public Double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Double cantidad) {
        Cantidad = cantidad;
    }

    public String getLoteLab() {
        return LoteLab;
    }

    public void setLoteLab(String loteLab) {
        LoteLab = loteLab;
    }

    public String getLotePT() {
        return LotePT;
    }

    public void setLotePT(String lotePT) {
        LotePT = lotePT;
    }

    public Boolean getFlagDisponible() {
        return FlagDisponible;
    }

    public void setFlagDisponible(Boolean flagDisponible) {
        FlagDisponible = flagDisponible;
    }

    public Boolean getFlagAveriado() {
        return FlagAveriado;
    }

    public void setFlagAveriado(Boolean flagAveriado) {
        FlagAveriado = flagAveriado;
    }

    public Integer getId_Marca() {
        return Id_Marca;
    }

    public void setId_Marca(Integer id_Marca) {
        Id_Marca = id_Marca;
    }

    public Boolean getFlagCuarentena() {
        return FlagCuarentena;
    }

    public void setFlagCuarentena(Boolean flagCuarentena) {
        FlagCuarentena = flagCuarentena;
    }

    public Integer getId_Condicion() {
        return Id_Condicion;
    }

    public void setId_Condicion(Integer id_Condicion) {
        Id_Condicion = id_Condicion;
    }

    public Date getFechaEmision() {
        return FechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        FechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        FechaVencimiento = fechaVencimiento;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String serie) {
        Serie = serie;
    }
}
