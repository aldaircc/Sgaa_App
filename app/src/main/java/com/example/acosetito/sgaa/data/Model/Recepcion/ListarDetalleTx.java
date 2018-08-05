package com.example.acosetito.sgaa.data.Model.Recepcion;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ListarDetalleTx {

    @SerializedName("Id_Tx")
    private String Id_Tx;

    @SerializedName("Id_Producto")
    private Integer Id_Producto;

    @SerializedName("Codigo")
    private String Codigo;

    @SerializedName("Descripcion")
    private String Descripcion;

    @SerializedName("FechaEmision")
    private Date FechaEmision;

    @SerializedName("FechaVencimiento")
    private Date FechaVencimiento;

    @SerializedName("Id_UM")
    private Integer Id_UM;

    @SerializedName("UM")
    private String UM;

    @SerializedName("Alias")
    private String Alias;

    @SerializedName("Lote")
    private String Lote;

    @SerializedName("EAN13")
    private String EAN13;

    @SerializedName("EAN14")
    private String EAN14;

    @SerializedName("CantidadPedida")
    private Double CantidadPedida;

    @SerializedName("Saldo")
    private Double Saldo;

    @SerializedName("CantidadOperacion")
    private Double CantidadOperacion;

    @SerializedName("CantidadBulto")
    private Double CantidadBulto;

    @SerializedName("CantidadXBulto")
    private Double CantidadXBulto;

    @SerializedName("TipoAlmacenaje")
    private String TipoAlmacenaje;

    @SerializedName("Id_UMB")
    private Integer Id_UMB;

    @SerializedName("UMBase")
    private String UMBase;

    @SerializedName("Factor")
    private Double Factor;

    @SerializedName("Item")
    private Integer Item;

    @SerializedName("Id_Composicion")
    private Integer Id_Composicion;

    @SerializedName("Composicion")
    private String Composicion;

    @SerializedName("Id_Ubicacion")
    private Integer Id_Ubicacion;

    @SerializedName("Id_Pasillo")
    private Integer Id_Pasillo;

    @SerializedName("Fila")
    private String Fila;

    @SerializedName("Columna")
    private Integer Columna;

    @SerializedName("Nivel")
    private Integer Nivel;

    @SerializedName("Posicion")
    private Integer Posicion;

    @SerializedName("Position")
    private String Position;

    @SerializedName("Inspeccion")
    private String Inspeccion;

    @SerializedName("ItemSAP")
    private String ItemSAP;

    @SerializedName("CondicionSAP")
    private String CondicionSAP;

    @SerializedName("CondicionAlmacenamiento")
    private String CondicionAlmacenamiento;

    public String getId_Tx() {
        return Id_Tx;
    }

    public void setId_Tx(String id_Tx) {
        Id_Tx = id_Tx;
    }

    public Integer getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(Integer id_Producto) {
        Id_Producto = id_Producto;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
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

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public String getLote() {
        return Lote;
    }

    public void setLote(String lote) {
        Lote = lote;
    }

    public String getEAN13() {
        return EAN13;
    }

    public void setEAN13(String EAN13) {
        this.EAN13 = EAN13;
    }

    public String getEAN14() {
        return EAN14;
    }

    public void setEAN14(String EAN14) {
        this.EAN14 = EAN14;
    }

    public Double getCantidadPedida() {
        return CantidadPedida;
    }

    public void setCantidadPedida(Double cantidadPedida) {
        CantidadPedida = cantidadPedida;
    }

    public Double getSaldo() {
        return Saldo;
    }

    public void setSaldo(Double saldo) {
        Saldo = saldo;
    }

    public Double getCantidadOperacion() {
        return CantidadOperacion;
    }

    public void setCantidadOperacion(Double cantidadOperacion) {
        CantidadOperacion = cantidadOperacion;
    }

    public Double getCantidadBulto() {
        return CantidadBulto;
    }

    public void setCantidadBulto(Double cantidadBulto) {
        CantidadBulto = cantidadBulto;
    }

    public Double getCantidadXBulto() {
        return CantidadXBulto;
    }

    public void setCantidadXBulto(Double cantidadXBulto) {
        CantidadXBulto = cantidadXBulto;
    }

    public String getTipoAlmacenaje() {
        return TipoAlmacenaje;
    }

    public void setTipoAlmacenaje(String tipoAlmacenaje) {
        TipoAlmacenaje = tipoAlmacenaje;
    }

    public Integer getId_UMB() {
        return Id_UMB;
    }

    public void setId_UMB(Integer id_UMB) {
        Id_UMB = id_UMB;
    }

    public String getUMBase() {
        return UMBase;
    }

    public void setUMBase(String UMBase) {
        this.UMBase = UMBase;
    }

    public Double getFactor() {
        return Factor;
    }

    public void setFactor(Double factor) {
        Factor = factor;
    }

    public Integer getItem() {
        return Item;
    }

    public void setItem(Integer item) {
        Item = item;
    }

    public Integer getId_Composicion() {
        return Id_Composicion;
    }

    public void setId_Composicion(Integer id_Composicion) {
        Id_Composicion = id_Composicion;
    }

    public String getComposicion() {
        return Composicion;
    }

    public void setComposicion(String composicion) {
        Composicion = composicion;
    }

    public Integer getId_Ubicacion() {
        return Id_Ubicacion;
    }

    public void setId_Ubicacion(Integer id_Ubicacion) {
        Id_Ubicacion = id_Ubicacion;
    }

    public Integer getId_Pasillo() {
        return Id_Pasillo;
    }

    public void setId_Pasillo(Integer id_Pasillo) {
        Id_Pasillo = id_Pasillo;
    }

    public String getFila() {
        return Fila;
    }

    public void setFila(String fila) {
        Fila = fila;
    }

    public Integer getColumna() {
        return Columna;
    }

    public void setColumna(Integer columna) {
        Columna = columna;
    }

    public Integer getNivel() {
        return Nivel;
    }

    public void setNivel(Integer nivel) {
        Nivel = nivel;
    }

    public Integer getPosicion() {
        return Posicion;
    }

    public void setPosicion(Integer posicion) {
        Posicion = posicion;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getInspeccion() {
        return Inspeccion;
    }

    public void setInspeccion(String inspeccion) {
        Inspeccion = inspeccion;
    }

    public String getItemSAP() {
        return ItemSAP;
    }

    public void setItemSAP(String itemSAP) {
        ItemSAP = itemSAP;
    }

    public String getCondicionSAP() {
        return CondicionSAP;
    }

    public void setCondicionSAP(String condicionSAP) {
        CondicionSAP = condicionSAP;
    }

    public String getCondicionAlmacenamiento() {
        return CondicionAlmacenamiento;
    }

    public void setCondicionAlmacenamiento(String condicionAlmacenamiento) {
        CondicionAlmacenamiento = condicionAlmacenamiento;
    }

    public Integer getId_Condicion() {
        return Id_Condicion;
    }

    public void setId_Condicion(Integer id_Condicion) {
        Id_Condicion = id_Condicion;
    }

    public String getCondicion() {
        return Condicion;
    }

    public void setCondicion(String condicion) {
        Condicion = condicion;
    }

    public String getPosicionReferencia() {
        return PosicionReferencia;
    }

    public void setPosicionReferencia(String posicionReferencia) {
        PosicionReferencia = posicionReferencia;
    }

    public Boolean getFlagLotePT() {
        return FlagLotePT;
    }

    public void setFlagLotePT(Boolean flagLotePT) {
        FlagLotePT = flagLotePT;
    }

    public Boolean getFlagSeriePT() {
        return FlagSeriePT;
    }

    public void setFlagSeriePT(Boolean flagSeriePT) {
        FlagSeriePT = flagSeriePT;
    }

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

    @SerializedName("Id_Condicion")
    private Integer Id_Condicion;
    @SerializedName("Condicion")
    private String Condicion;
    @SerializedName("PosicionReferencia")
    private String PosicionReferencia;
    @SerializedName("FlagLotePT")
    private Boolean FlagLotePT;
    @SerializedName("FlagSeriePT")
    private Boolean FlagSeriePT;
    @SerializedName("Id_SubAlmacen")
    private Integer Id_SubAlmacen;
    @SerializedName("NombreSubAlmacen")
    private String NombreSubAlmacen;
}
