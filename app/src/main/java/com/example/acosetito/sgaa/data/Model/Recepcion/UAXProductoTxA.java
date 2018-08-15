package com.example.acosetito.sgaa.data.Model.Recepcion;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UAXProductoTxA implements Comparable, Cloneable{
    @SerializedName("Id_Tx")
    private String Id_Tx;
    @SerializedName("Contenedor")
    private String Contenedor;
    @SerializedName("UA")
    private String UA;
    @SerializedName("Cantidad")
    private Float Cantidad;
    @SerializedName("LoteLab")
    private String LoteLab;
    @SerializedName("FechaIngreso")
    private Date FechaIngreso;
    @SerializedName("CantidadAveriada")
    private Float CantidadAveriada;
    @SerializedName("Id_Producto")
    private int Id_Producto;
    @SerializedName("LotePT")
    private String LotePT;
    @SerializedName("Usuario")
    private String Usuario;
    @SerializedName("Condicion")
    private String Condicion;
    @SerializedName("CondicionAlmacenamiento")
    private String CondicionAlmacenamiento;
    @SerializedName("NroCajas")
    private int NroCajas;
    @SerializedName("UndXCajas")
    private int UndXCajas;
    @SerializedName("SaldoTotal")
    private int SaldoTotal;
    @SerializedName("TipoEAN")
    private String TipoEAN;
    @SerializedName("EAN")
    private String EAN;
    @SerializedName("TipoAlmacenaje")
    private String TipoAlmacenaje;
    @SerializedName("Id_causal")
    private Integer Id_causal;
    @SerializedName("Causal")
    private String Causal;
    @SerializedName("UA_CodBarra")
    private String UA_CodBarra;
    @SerializedName("ApeNom")
    private String ApeNom;

    public String getId_Tx() {
        return Id_Tx;
    }

    public void setId_Tx(String id_Tx) {
        Id_Tx = id_Tx;
    }

    public String getContenedor() {
        return Contenedor;
    }

    public void setContenedor(String contenedor) {
        Contenedor = contenedor;
    }

    public String getUA() {
        return UA;
    }

    public void setUA(String UA) {
        this.UA = UA;
    }

    public Float getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Float cantidad) {
        Cantidad = cantidad;
    }

    public String getLoteLab() {
        return LoteLab;
    }

    public void setLoteLab(String loteLab) {
        LoteLab = loteLab;
    }

    public Date getFechaIngreso() {
        return FechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        FechaIngreso = fechaIngreso;
    }

    public Float getCantidadAveriada() {
        return CantidadAveriada;
    }

    public void setCantidadAveriada(Float cantidadAveriada) {
        CantidadAveriada = cantidadAveriada;
    }

    public int getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        Id_Producto = id_Producto;
    }

    public String getLotePT() {
        return LotePT;
    }

    public void setLotePT(String lotePT) {
        LotePT = lotePT;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getCondicion() {
        return Condicion;
    }

    public void setCondicion(String condicion) {
        Condicion = condicion;
    }

    public String getCondicionAlmacenamiento() {
        return CondicionAlmacenamiento;
    }

    public void setCondicionAlmacenamiento(String condicionAlmacenamiento) {
        CondicionAlmacenamiento = condicionAlmacenamiento;
    }

    public int getNroCajas() {
        return NroCajas;
    }

    public void setNroCajas(int nroCajas) {
        NroCajas = nroCajas;
    }

    public int getUndXCajas() {
        return UndXCajas;
    }

    public void setUndXCajas(int undXCajas) {
        UndXCajas = undXCajas;
    }

    public int getSaldoTotal() {
        return SaldoTotal;
    }

    public void setSaldoTotal(int saldoTotal) {
        SaldoTotal = saldoTotal;
    }

    public String getTipoEAN() {
        return TipoEAN;
    }

    public void setTipoEAN(String tipoEAN) {
        TipoEAN = tipoEAN;
    }

    public String getEAN() {
        return EAN;
    }

    public void setEAN(String EAN) {
        this.EAN = EAN;
    }

    public String getTipoAlmacenaje() {
        return TipoAlmacenaje;
    }

    public void setTipoAlmacenaje(String tipoAlmacenaje) {
        TipoAlmacenaje = tipoAlmacenaje;
    }

    public Integer getId_causal() {
        return Id_causal;
    }

    public void setId_causal(Integer id_causal) {
        Id_causal = id_causal;
    }

    public String getCausal() {
        return Causal;
    }

    public void setCausal(String causal) {
        Causal = causal;
    }

    public String getUA_CodBarra() {
        return UA_CodBarra;
    }

    public void setUA_CodBarra(String UA_CodBarra) {
        this.UA_CodBarra = UA_CodBarra;
    }

    public String getApeNom() {
        return ApeNom;
    }

    public void setApeNom(String apeNom) {
        ApeNom = apeNom;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        UAXProductoTxA compare = (UAXProductoTxA) o;
        //if (compare.Id_Tx == this.Id_Tx && compare.SaldoTotal == (this.SaldoTotal)) {
        if (compare.Id_Tx.equals(this.Id_Tx) && compare.SaldoTotal == (this.SaldoTotal)) {
            return 0;
        }
        return 1;
    }

    @Override
    public UAXProductoTxA clone() {
        UAXProductoTxA clone;
        try {
            clone = (UAXProductoTxA) super.clone();
        }catch (CloneNotSupportedException e){
            throw new RuntimeException(e);
        }
        return clone;
    }
}
