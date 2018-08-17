package com.example.acosetito.sgaa.data.Model.Impresora;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AccesosImpresoraXUsuario implements Comparable, Cloneable{
    @SerializedName("Nombre")
    private String Nombre;
    @SerializedName("Acceso")
    private Integer Acceso;
    @SerializedName("Id_Impresora")
    private Integer Id_Impresora;
    @SerializedName("IpImpresora")
    private String IpImpresora;
    @SerializedName("PuertoImpresora")
    private String PuertoImpresora;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Integer getAcceso() {
        return Acceso;
    }

    public void setAcceso(Integer acceso) {
        Acceso = acceso;
    }

    public Integer getId_Impresora() {
        return Id_Impresora;
    }

    public void setId_Impresora(Integer id_Impresora) {
        Id_Impresora = id_Impresora;
    }

    public String getIpImpresora() {
        return IpImpresora;
    }

    public void setIpImpresora(String ipImpresora) {
        IpImpresora = ipImpresora;
    }

    public String getPuertoImpresora() {
        return PuertoImpresora;
    }

    public void setPuertoImpresora(String puertoImpresora) {
        PuertoImpresora = puertoImpresora;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        AccesosImpresoraXUsuario compare = (AccesosImpresoraXUsuario) o;
        if (compare.Id_Impresora.equals(this.Id_Impresora) && compare.Nombre.equals(this.Nombre)
                && compare.IpImpresora.equals(this.IpImpresora) && compare.PuertoImpresora.equals(this.PuertoImpresora)){
            return 0;
        }
        return 1;
    }

    @Override
    public AccesosImpresoraXUsuario clone(){
        AccesosImpresoraXUsuario clone;
     try {
        clone = (AccesosImpresoraXUsuario)super.clone();
     }catch (CloneNotSupportedException ex){
         throw  new RuntimeException(ex);
     }
     return clone;
    }
}
