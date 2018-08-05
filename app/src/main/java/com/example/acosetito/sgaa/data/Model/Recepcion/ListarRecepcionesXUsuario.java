package com.example.acosetito.sgaa.data.Model.Recepcion;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ListarRecepcionesXUsuario {
    @SerializedName("Id_Tx")
    private String Id_Tx ;
    @SerializedName("NumOrden")
    private String NumOrden ;
    @SerializedName("FechaDocumento")
    private Date FechaDocumento ;
    @SerializedName("Id_TipoMovimiento")
    private Integer Id_TipoMovimiento ;
    @SerializedName("TipoMovimiento")
    private String TipoMovimiento ;
    @SerializedName("Id_Estado")
    private Integer Id_Estado ;
    @SerializedName("Estado")
    private String Estado ;
    @SerializedName("Id_TipoDocumento")
    private Integer Id_TipoDocumento ;
    @SerializedName("TipoDocumento")
    private String TipoDocumento ;
    @SerializedName("Id_Muelle")
    private Integer Id_Muelle ;
    @SerializedName("Muelle")
    private String Muelle ;
    @SerializedName("CodBarraMuelle")
    private String CodBarraMuelle ;
    @SerializedName("Id_Cliente")
    private Integer Id_Cliente ;
    @SerializedName("Cliente")
    private String Cliente ;
    @SerializedName("Id_Proveedor")
    private Integer Id_Proveedor ;
    @SerializedName("Proveedor")
    private String Proveedor ;
    @SerializedName("FechaLlegada")
    private Date FechaLlegada ;
    @SerializedName("Observacion")
    private String Observacion ;
    @SerializedName("FlagPausa")
    private Boolean FlagPausa ;
    @SerializedName("FlagDetalle")
    private Boolean FlagDetalle ;

    public String getId_Tx() {
        return Id_Tx;
    }

    public void setId_Tx(String id_Tx) {
        Id_Tx = id_Tx;
    }

    public String getNumOrden() {
        return NumOrden;
    }

    public void setNumOrden(String numOrden) {
        NumOrden = numOrden;
    }

    public Date getFechaDocumento() {
        return FechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        FechaDocumento = fechaDocumento;
    }

    public Integer getId_TipoMovimiento() {
        return Id_TipoMovimiento;
    }

    public void setId_TipoMovimiento(Integer id_TipoMovimiento) {
        Id_TipoMovimiento = id_TipoMovimiento;
    }

    public String getTipoMovimiento() {
        return TipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        TipoMovimiento = tipoMovimiento;
    }

    public Integer getId_Estado() {
        return Id_Estado;
    }

    public void setId_Estado(Integer id_Estado) {
        Id_Estado = id_Estado;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public Integer getId_TipoDocumento() {
        return Id_TipoDocumento;
    }

    public void setId_TipoDocumento(Integer id_TipoDocumento) {
        Id_TipoDocumento = id_TipoDocumento;
    }

    public String getTipoDocumento() {
        return TipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        TipoDocumento = tipoDocumento;
    }

    public Integer getId_Muelle() {
        return Id_Muelle;
    }

    public void setId_Muelle(Integer id_Muelle) {
        Id_Muelle = id_Muelle;
    }

    public String getMuelle() {
        return Muelle;
    }

    public void setMuelle(String muelle) {
        Muelle = muelle;
    }

    public String getCodBarraMuelle() {
        return CodBarraMuelle;
    }

    public void setCodBarraMuelle(String codBarraMuelle) {
        CodBarraMuelle = codBarraMuelle;
    }

    public Integer getId_Cliente() {
        return Id_Cliente;
    }

    public void setId_Cliente(Integer id_Cliente) {
        Id_Cliente = id_Cliente;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public Integer getId_Proveedor() {
        return Id_Proveedor;
    }

    public void setId_Proveedor(Integer id_Proveedor) {
        Id_Proveedor = id_Proveedor;
    }

    public String getProveedor() {
        return Proveedor;
    }

    public void setProveedor(String proveedor) {
        Proveedor = proveedor;
    }

    public Date getFechaLlegada() {
        return FechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        FechaLlegada = fechaLlegada;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    public Boolean getFlagPausa() {
        return FlagPausa;
    }

    public void setFlagPausa(Boolean flagPausa) {
        FlagPausa = flagPausa;
    }

    public Boolean getFlagDetalle() {
        return FlagDetalle;
    }

    public void setFlagDetalle(Boolean flagDetalle) {
        FlagDetalle = flagDetalle;
    }
}
