package com.example.acosetito.sgaa.data.Model.Recepcion;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class TxUbicacion {
    @SerializedName("Id_Tx")
    private String Id_Tx;
    @SerializedName("Id_TipoMovimiento")
    private Integer Id_TipoMovimiento;
    @SerializedName("Prioridad")
    private Integer Prioridad;
    @SerializedName("Item")
    private Integer Item;
    @SerializedName("Id_Producto")
    private Integer Id_Producto;
    @SerializedName("Lote")
    private String Lote;
    @SerializedName("Cantidad")
    private Double Cantidad;
    @SerializedName("Id_Condicion")
    private Integer Id_Condicion;
    @SerializedName("Id_Almacen")
    private Integer Id_Almacen;
    @SerializedName("Id_Ubicacion_Destino")
    private Integer Id_Ubicacion_Destino;
    @SerializedName("Id_Ubicacion_Origen")
    private Integer Id_Ubicacion_Origen;
    @SerializedName("TipoUbicacion")
    private Integer TipoUbicacion;
    @SerializedName("UsuarioRegistro")
    private String UsuarioRegistro;
    @SerializedName("FechaRegistro")
    private Date FechaRegistro;
    @SerializedName("UsuarioModificacion")
    private String UsuarioModificacion;
    @SerializedName("FechaModificacion")
    private Date FechaModificacion;
    @SerializedName("UsuarioAnulacion")
    private String UsuarioAnulacion;
    @SerializedName("FechaAnulacion")
    private Date FechaAnulacion;
    @SerializedName("FlagAnulado")
    private Boolean FlagAnulado;
    @SerializedName("Id_Estado")
    private Integer Id_Estado;
    @SerializedName("Observacion")
    private String Observacion;
    @SerializedName("UsuarioAsignado")
    private String UsuarioAsignado;
    @SerializedName("FechaHoraInicio")
    private Date FechaHoraInicio;
    @SerializedName("FechaHoraFin")
    private Date FechaHoraFin;
    @SerializedName("Id_TerminalRF")
    private Integer Id_TerminalRF;

    public String getId_Tx() {
        return Id_Tx;
    }

    public void setId_Tx(String id_Tx) {
        Id_Tx = id_Tx;
    }

    public Integer getId_TipoMovimiento() {
        return Id_TipoMovimiento;
    }

    public void setId_TipoMovimiento(Integer id_TipoMovimiento) {
        Id_TipoMovimiento = id_TipoMovimiento;
    }

    public Integer getPrioridad() {
        return Prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        Prioridad = prioridad;
    }

    public Integer getItem() {
        return Item;
    }

    public void setItem(Integer item) {
        Item = item;
    }

    public Integer getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(Integer id_Producto) {
        Id_Producto = id_Producto;
    }

    public String getLote() {
        return Lote;
    }

    public void setLote(String lote) {
        Lote = lote;
    }

    public Double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Double cantidad) {
        Cantidad = cantidad;
    }

    public Integer getId_Condicion() {
        return Id_Condicion;
    }

    public void setId_Condicion(Integer id_Condicion) {
        Id_Condicion = id_Condicion;
    }

    public Integer getId_Almacen() {
        return Id_Almacen;
    }

    public void setId_Almacen(Integer id_Almacen) {
        Id_Almacen = id_Almacen;
    }

    public Integer getId_Ubicacion_Destino() {
        return Id_Ubicacion_Destino;
    }

    public void setId_Ubicacion_Destino(Integer id_Ubicacion_Destino) {
        Id_Ubicacion_Destino = id_Ubicacion_Destino;
    }

    public Integer getId_Ubicacion_Origen() {
        return Id_Ubicacion_Origen;
    }

    public void setId_Ubicacion_Origen(Integer id_Ubicacion_Origen) {
        Id_Ubicacion_Origen = id_Ubicacion_Origen;
    }

    public Integer getTipoUbicacion() {
        return TipoUbicacion;
    }

    public void setTipoUbicacion(Integer tipoUbicacion) {
        TipoUbicacion = tipoUbicacion;
    }

    public String getUsuarioRegistro() {
        return UsuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        UsuarioRegistro = usuarioRegistro;
    }

    public Date getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }

    public String getUsuarioModificacion() {
        return UsuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        UsuarioModificacion = usuarioModificacion;
    }

    public Date getFechaModificacion() {
        return FechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        FechaModificacion = fechaModificacion;
    }

    public String getUsuarioAnulacion() {
        return UsuarioAnulacion;
    }

    public void setUsuarioAnulacion(String usuarioAnulacion) {
        UsuarioAnulacion = usuarioAnulacion;
    }

    public Date getFechaAnulacion() {
        return FechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        FechaAnulacion = fechaAnulacion;
    }

    public Boolean getFlagAnulado() {
        return FlagAnulado;
    }

    public void setFlagAnulado(Boolean flagAnulado) {
        FlagAnulado = flagAnulado;
    }

    public Integer getId_Estado() {
        return Id_Estado;
    }

    public void setId_Estado(Integer id_Estado) {
        Id_Estado = id_Estado;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    public String getUsuarioAsignado() {
        return UsuarioAsignado;
    }

    public void setUsuarioAsignado(String usuarioAsignado) {
        UsuarioAsignado = usuarioAsignado;
    }

    public Date getFechaHoraInicio() {
        return FechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        FechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return FechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        FechaHoraFin = fechaHoraFin;
    }

    public Integer getId_TerminalRF() {
        return Id_TerminalRF;
    }

    public void setId_TerminalRF(Integer id_TerminalRF) {
        Id_TerminalRF = id_TerminalRF;
    }
}
