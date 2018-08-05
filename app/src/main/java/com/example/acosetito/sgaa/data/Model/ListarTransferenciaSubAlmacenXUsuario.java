package com.example.acosetito.sgaa.data.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class ListarTransferenciaSubAlmacenXUsuario {
    @Expose
    @SerializedName("Id_Tx")
    private String Id_Tx;
    @Expose
    @SerializedName("FechaDocumento")
    private Date FechaDocumento;
    @Expose
    @SerializedName("Id_Estado")
    private Integer Id_Estado;
    @Expose
    @SerializedName("Estado")
    private String Estado;
    @Expose
    @SerializedName("Id_Cuenta")
    private Integer Id_Cuenta;
    @Expose
    @SerializedName("Cuenta")
    private String Cuenta;
    @Expose
    @SerializedName("Motivo")
    private String Motivo;
    @Expose
    @SerializedName("Observacion")
    private String Observacion;
    @Expose
    @SerializedName("Id_SubAlmacenOrigen")
    private Integer Id_SubAlmacenOrigen;
    @Expose
    @SerializedName("Id_SubAlmacenDestino")
    private Integer Id_SubAlmacenDestino;
    @Expose
    @SerializedName("SubAlmacenOrigen")
    private String SubAlmacenOrigen;
    @Expose
    @SerializedName("SubAlmacenDestino")
    private String SubAlmacenDestino;


    public String getId_Tx() {
        return Id_Tx;
    }

    public void setId_Tx(String id_Tx) {
        Id_Tx = id_Tx;
    }

    public Date getFechaDocumento() {
        return FechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        FechaDocumento = fechaDocumento;
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

    public Integer getId_Cuenta() {
        return Id_Cuenta;
    }

    public void setId_Cuenta(Integer id_Cuenta) {
        Id_Cuenta = id_Cuenta;
    }

    public String getCuenta() {
        return Cuenta;
    }

    public void setCuenta(String cuenta) {
        Cuenta = cuenta;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String motivo) {
        Motivo = motivo;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    public Integer getId_SubAlmacenOrigen() {
        return Id_SubAlmacenOrigen;
    }

    public void setId_SubAlmacenOrigen(Integer id_SubAlmacenOrigen) {
        Id_SubAlmacenOrigen = id_SubAlmacenOrigen;
    }

    public Integer getId_SubAlmacenDestino() {
        return Id_SubAlmacenDestino;
    }

    public void setId_SubAlmacenDestino(Integer id_SubAlmacenDestino) {
        Id_SubAlmacenDestino = id_SubAlmacenDestino;
    }

    public String getSubAlmacenOrigen() {
        return SubAlmacenOrigen;
    }

    public void setSubAlmacenOrigen(String subAlmacenOrigen) {
        SubAlmacenOrigen = subAlmacenOrigen;
    }

    public String getSubAlmacenDestino() {
        return SubAlmacenDestino;
    }

    public void setSubAlmacenDestino(String subAlmacenDestino) {
        SubAlmacenDestino = subAlmacenDestino;
    }
}
