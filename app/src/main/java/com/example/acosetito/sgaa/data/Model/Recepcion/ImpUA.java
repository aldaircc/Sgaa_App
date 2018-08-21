package com.example.acosetito.sgaa.data.Model.Recepcion;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ImpUA {

    @SerializedName("UA_CodBarra")
    private String UA_CodBarra;
    @SerializedName("Id_Producto")
    private Integer Id_Producto;
    @SerializedName("Codigo")
    private String Codigo;
    @SerializedName("Producto")
    private String Producto;
    @SerializedName("Item")
    private Integer Item;
    @SerializedName("Cantidad")
    private Double Cantidad;
    @SerializedName("Saldo")
    private Double Saldo;
    @SerializedName("LoteLab")
    private String LoteLab;
    @SerializedName("LotePT")
    private String LotePT;
    @SerializedName("FechaEmision")
    private Date FechaEmision;
    @SerializedName("FechaVencimiento")
    private Date FechaVencimiento;
    @SerializedName("Id_Estado")
    private Integer Id_Estado;
    @SerializedName("FechaRegistro")
    private Date FechaRegistro;
    @SerializedName("UsuarioRegistro")
    private String UsuarioRegistro;
    @SerializedName("CantidadEtiqueta")
    private Integer CantidadEtiqueta;
    @SerializedName("SaldoEtiqueta")
    private Double SaldoEtiqueta;
    @SerializedName("Id_Causal")
    private Integer Id_Causal;
    @SerializedName("Id_Almacen")
    private Integer Id_Almacen;
    @SerializedName("Id_TerminalRF")
    private Integer Id_TerminalRF;
    @SerializedName("PalletCodBarra")
    private String PalletCodBarra;
    @SerializedName("Id_Tx_Ubi")
    private String Id_Tx_Ubi;
    @SerializedName("Id_SubAlmacen")
    private Integer Id_SubAlmacen;

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

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        Producto = producto;
    }

    public Integer getItem() {
        return Item;
    }

    public void setItem(Integer item) {
        Item = item;
    }

    public Double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Double cantidad) {
        Cantidad = cantidad;
    }

    public Double getSaldo() {
        return Saldo;
    }

    public void setSaldo(Double saldo) {
        Saldo = saldo;
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

    public Integer getId_Estado() {
        return Id_Estado;
    }

    public void setId_Estado(Integer id_Estado) {
        Id_Estado = id_Estado;
    }

    public Date getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }

    public String getUsuarioRegistro() {
        return UsuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        UsuarioRegistro = usuarioRegistro;
    }

    public Integer getCantidadEtiqueta() {
        return CantidadEtiqueta;
    }

    public void setCantidadEtiqueta(Integer cantidadEtiqueta) {
        CantidadEtiqueta = cantidadEtiqueta;
    }

    public Double getSaldoEtiqueta() {
        return SaldoEtiqueta;
    }

    public void setSaldoEtiqueta(Double saldoEtiqueta) {
        SaldoEtiqueta = saldoEtiqueta;
    }

    public Integer getId_Causal() {
        return Id_Causal;
    }

    public void setId_Causal(Integer id_Causal) {
        Id_Causal = id_Causal;
    }

    public Integer getId_Almacen() {
        return Id_Almacen;
    }

    public void setId_Almacen(Integer id_Almacen) {
        Id_Almacen = id_Almacen;
    }

    public Integer getId_TerminalRF() {
        return Id_TerminalRF;
    }

    public void setId_TerminalRF(Integer id_TerminalRF) {
        Id_TerminalRF = id_TerminalRF;
    }

    public String getPalletCodBarra() {
        return PalletCodBarra;
    }

    public void setPalletCodBarra(String palletCodBarra) {
        PalletCodBarra = palletCodBarra;
    }

    public String getId_Tx_Ubi() {
        return Id_Tx_Ubi;
    }

    public void setId_Tx_Ubi(String id_Tx_Ubi) {
        Id_Tx_Ubi = id_Tx_Ubi;
    }

    public Integer getId_SubAlmacen() {
        return Id_SubAlmacen;
    }

    public void setId_SubAlmacen(Integer id_SubAlmacen) {
        Id_SubAlmacen = id_SubAlmacen;
    }
}
