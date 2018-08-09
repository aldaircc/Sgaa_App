package com.example.acosetito.sgaa.data.Model.Recepcion;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UA {
    @SerializedName("UA_CodBarra")
    private String UA_CodBarra ;
    @SerializedName("PalletCodBarra")
    private String PalletCodBarra ;
    @SerializedName("Id_Producto")
    private Integer Id_Producto ;
    @SerializedName("Codigo")
    private String Codigo ;
    @SerializedName("Producto")
    private String Producto ;
    @SerializedName("Id_UM")
    private Integer Id_UM ;
    @SerializedName("Id_Ubicacion")
    private Integer Id_Ubicacion ;
    @SerializedName("Id_UbicacionOld")
    private Integer Id_UbicacionOld ;
    @SerializedName("Id_Tx")
    private String Id_Tx ;
    @SerializedName("Id_Tx_Ubi")
    private String Id_Tx_Ubi ;
    @SerializedName("Id_Tx_Anterior")
    private String Id_Tx_Anterior ;
    @SerializedName("FlagActivo")
    private Boolean FlagActivo ;
    @SerializedName("Item")
    private Integer Item ;
    @SerializedName("Cantidad")
    private Double Cantidad ;
    @SerializedName("Saldo")
    private Double Saldo ;
    @SerializedName("CantidadAveriada")
    private Double CantidadAveriada ;
    @SerializedName("LoteLab")
    private String LoteLab ;
    @SerializedName("LotePT")
    private String LotePT ;
    @SerializedName("FechaEmision")
    private Date FechaEmision ;
    @SerializedName("FechaVencimiento")
    private Date FechaVencimiento ;
    @SerializedName("FlagDisponible")
    private Boolean FlagDisponible ;
    @SerializedName("FlagAveriado")
    private Boolean FlagAveriado ;
    @SerializedName("FechaIngreso")
    private Date FechaIngreso ;
    @SerializedName("Nota")
    private String Nota ;
    @SerializedName("Id_Estado")
    private Integer Id_Estado ;
    @SerializedName("Observacion")
    private String Observacion ;
    @SerializedName("Id_TerminalRF")
    private Integer Id_TerminalRF ;
    @SerializedName("Id_TerminalRF_Pallet")
    private Integer Id_TerminalRF_Pallet ;
    @SerializedName("FecRegIdTerminalRF_Pallet")
    private Date FecRegIdTerminalRF_Pallet ;
    @SerializedName("UsuarioRegistro")
    private String UsuarioRegistro ;
    @SerializedName("FechaRegistro")
    private Date FechaRegistro ;
    @SerializedName("UsuarioModificacion")
    private String UsuarioModificacion ;
    @SerializedName("FechaModificacion")
    private Date FechaModificacion ;
    @SerializedName("UsuarioAnulacion")
    private String UsuarioAnulacion ;
    @SerializedName("FechaAnulacion")
    private Date FechaAnulacion ;
    @SerializedName("FlagAnulado")
    private Boolean FlagAnulado ;
    @SerializedName("Id_Almacen")
    private Integer Id_Almacen ;
    @SerializedName("Id_Marca")
    private Integer Id_Marca ;
    @SerializedName("BarraUbicacion")
    private String BarraUbicacion ;
    @SerializedName("Accion")
    private String Accion ;
    @SerializedName("Serie")
    private String Serie ;

    public String getUA_CodBarra() {
        return UA_CodBarra;
    }

    public void setUA_CodBarra(String UA_CodBarra) {
        this.UA_CodBarra = UA_CodBarra;
    }

    public String getPalletCodBarra() {
        return PalletCodBarra;
    }

    public void setPalletCodBarra(String palletCodBarra) {
        PalletCodBarra = palletCodBarra;
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

    public Integer getId_UM() {
        return Id_UM;
    }

    public void setId_UM(Integer id_UM) {
        Id_UM = id_UM;
    }

    public Integer getId_Ubicacion() {
        return Id_Ubicacion;
    }

    public void setId_Ubicacion(Integer id_Ubicacion) {
        Id_Ubicacion = id_Ubicacion;
    }

    public Integer getId_UbicacionOld() {
        return Id_UbicacionOld;
    }

    public void setId_UbicacionOld(Integer id_UbicacionOld) {
        Id_UbicacionOld = id_UbicacionOld;
    }

    public String getId_Tx() {
        return Id_Tx;
    }

    public void setId_Tx(String id_Tx) {
        Id_Tx = id_Tx;
    }

    public String getId_Tx_Ubi() {
        return Id_Tx_Ubi;
    }

    public void setId_Tx_Ubi(String id_Tx_Ubi) {
        Id_Tx_Ubi = id_Tx_Ubi;
    }

    public String getId_Tx_Anterior() {
        return Id_Tx_Anterior;
    }

    public void setId_Tx_Anterior(String id_Tx_Anterior) {
        Id_Tx_Anterior = id_Tx_Anterior;
    }

    public Boolean getFlagActivo() {
        return FlagActivo;
    }

    public void setFlagActivo(Boolean flagActivo) {
        FlagActivo = flagActivo;
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

    public Double getCantidadAveriada() {
        return CantidadAveriada;
    }

    public void setCantidadAveriada(Double cantidadAveriada) {
        CantidadAveriada = cantidadAveriada;
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

    public Date getFechaIngreso() {
        return FechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        FechaIngreso = fechaIngreso;
    }

    public String getNota() {
        return Nota;
    }

    public void setNota(String nota) {
        Nota = nota;
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

    public Integer getId_TerminalRF() {
        return Id_TerminalRF;
    }

    public void setId_TerminalRF(Integer id_TerminalRF) {
        Id_TerminalRF = id_TerminalRF;
    }

    public Integer getId_TerminalRF_Pallet() {
        return Id_TerminalRF_Pallet;
    }

    public void setId_TerminalRF_Pallet(Integer id_TerminalRF_Pallet) {
        Id_TerminalRF_Pallet = id_TerminalRF_Pallet;
    }

    public Date getFecRegIdTerminalRF_Pallet() {
        return FecRegIdTerminalRF_Pallet;
    }

    public void setFecRegIdTerminalRF_Pallet(Date fecRegIdTerminalRF_Pallet) {
        FecRegIdTerminalRF_Pallet = fecRegIdTerminalRF_Pallet;
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

    public Integer getId_Almacen() {
        return Id_Almacen;
    }

    public void setId_Almacen(Integer id_Almacen) {
        Id_Almacen = id_Almacen;
    }

    public Integer getId_Marca() {
        return Id_Marca;
    }

    public void setId_Marca(Integer id_Marca) {
        Id_Marca = id_Marca;
    }

    public String getBarraUbicacion() {
        return BarraUbicacion;
    }

    public void setBarraUbicacion(String barraUbicacion) {
        BarraUbicacion = barraUbicacion;
    }

    public String getAccion() {
        return Accion;
    }

    public void setAccion(String accion) {
        Accion = accion;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String serie) {
        Serie = serie;
    }
}
