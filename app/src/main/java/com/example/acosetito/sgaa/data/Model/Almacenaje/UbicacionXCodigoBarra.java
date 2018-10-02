package com.example.acosetito.sgaa.data.Model.Almacenaje;
import com.google.gson.annotations.SerializedName;

public class UbicacionXCodigoBarra {
    @SerializedName("Id_Ubicacion")
    private Integer Id_Ubicacion;
    @SerializedName("Id_Sector")
    private Integer Id_Sector;
    @SerializedName("Sector")
    private String Sector;
    @SerializedName("Fila")
    private String Fila;
    @SerializedName("Columna")
    private Integer Columna;
    @SerializedName("Nivel")
    private Integer Nivel;
    @SerializedName("Posicion")
    private Integer Posicion;
    @SerializedName("Pasillo")
    private String Pasillo;
    @SerializedName("CodigoBarra")
    private String CodigoBarra;

    public Integer getId_Ubicacion() {
        return Id_Ubicacion;
    }

    public void setId_Ubicacion(Integer id_Ubicacion) {
        Id_Ubicacion = id_Ubicacion;
    }

    public Integer getId_Sector() {
        return Id_Sector;
    }

    public void setId_Sector(Integer id_Sector) {
        Id_Sector = id_Sector;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String sector) {
        Sector = sector;
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

    public String getPasillo() {
        return Pasillo;
    }

    public void setPasillo(String pasillo) {
        Pasillo = pasillo;
    }

    public String getCodigoBarra() {
        return CodigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        CodigoBarra = codigoBarra;
    }
}
