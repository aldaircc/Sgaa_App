package com.example.acosetito.sgaa.data.Model;

import com.google.gson.annotations.SerializedName;

public class Almacen {
    @SerializedName("Almacen")
    private String Almacen;
    @SerializedName("Cliente")
    private String Cliente;
    @SerializedName("CorreoSupervisor")
    private  String CorreoSupervisor;
    @SerializedName("Id_Almacen")
    private Integer Id_Almacen;
    @SerializedName("Id_Cliente")
    private Integer Id_Cliente;
    @SerializedName("NombreSuperior")
    private String NombreSuperior;

    public String getAlmacen() {
        return Almacen;
    }

    public void setAlmacenDescription(String almacenDescription) {
        Almacen = almacenDescription;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public String getCorreoSupervisor() {
        return CorreoSupervisor;
    }

    public void setCorreoSupervisor(String correoSupervisor) {
        CorreoSupervisor = correoSupervisor;
    }

    public Integer getId_Almacen() {
        return Id_Almacen;
    }

    public void setId_Almacen(Integer id_Almacen) {
        Id_Almacen = id_Almacen;
    }

    public Integer getId_Cliente() {
        return Id_Cliente;
    }

    public void setId_Cliente(Integer id_Cliente) {
        Id_Cliente = id_Cliente;
    }

    public String getNombreSuperior() {
        return NombreSuperior;
    }

    public void setNombreSuperior(String nombreSuperior) {
        NombreSuperior = nombreSuperior;
    }
}
