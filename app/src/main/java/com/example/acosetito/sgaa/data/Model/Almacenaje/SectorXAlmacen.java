package com.example.acosetito.sgaa.data.Model.Almacenaje;
import com.google.gson.annotations.SerializedName;

public class SectorXAlmacen {
    @SerializedName("Id_Sector")
    private Integer Id_Sector;
    @SerializedName("Sector")
    private String Sector;
    @SerializedName("Id_Almacen")
    private Integer Id_Almacen;
    @SerializedName("Almacen")
    private String Almacen;
    @SerializedName("FlagCuarentena")
    private Boolean FlagCuarentena;
    @SerializedName("Id_TipoAlmacenamiento")
    private Integer Id_TipoAlmacenamiento;
    @SerializedName("NombreAlmacenamiento")
    private String NombreAlmacenamiento;

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

    public Integer getId_Almacen() {
        return Id_Almacen;
    }

    public void setId_Almacen(Integer id_Almacen) {
        Id_Almacen = id_Almacen;
    }

    public String getAlmacen() {
        return Almacen;
    }

    public void setAlmacen(String almacen) {
        Almacen = almacen;
    }

    public Boolean getFlagCuarentena() {
        return FlagCuarentena;
    }

    public void setFlagCuarentena(Boolean flagCuarentena) {
        FlagCuarentena = flagCuarentena;
    }

    public Integer getId_TipoAlmacenamiento() {
        return Id_TipoAlmacenamiento;
    }

    public void setId_TipoAlmacenamiento(Integer id_TipoAlmacenamiento) {
        Id_TipoAlmacenamiento = id_TipoAlmacenamiento;
    }

    public String getNombreAlmacenamiento() {
        return NombreAlmacenamiento;
    }

    public void setNombreAlmacenamiento(String nombreAlmacenamiento) {
        NombreAlmacenamiento = nombreAlmacenamiento;
    }
}
