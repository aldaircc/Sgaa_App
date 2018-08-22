package com.example.acosetito.sgaa.data.Model.Recepcion;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class ControlPendiente {
    @SerializedName("Observacion")
    private String Observacion;
    @SerializedName("Id_Causal")
    private Integer Id_Causal;
    @SerializedName("FlagPausa")
    private Boolean FlagPausa;
    @SerializedName("FechaHoraInicio")
    private Date FechaHoraInicio;
    @SerializedName("Id_Tx")
    private String Id_Tx;

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    public Integer getId_Causal() {
        return Id_Causal;
    }

    public void setId_Causal(Integer id_Causal) {
        Id_Causal = id_Causal;
    }

    public Boolean getFlagPausa() {
        return FlagPausa;
    }

    public void setFlagPausa(Boolean flagPausa) {
        FlagPausa = flagPausa;
    }

    public Date getFechaHoraInicio() {
        return FechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        FechaHoraInicio = fechaHoraInicio;
    }

    public String getId_Tx() {
        return Id_Tx;
    }

    public void setId_Tx(String id_Tx) {
        Id_Tx = id_Tx;
    }
}
