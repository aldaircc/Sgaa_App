package com.example.acosetito.sgaa.data.Model;
import com.google.gson.annotations.SerializedName;

public class Usuario {
    @SerializedName("Usuario")
    private String Usuario;
    @SerializedName("ApeNom")
    private String ApeNom;
    @SerializedName("Correo")
    private String Correo;
    @SerializedName("Foto")
    private byte[] Foto;
    @SerializedName("Id_Perfil")
    private Integer Id_Perfil;
    @SerializedName("FlagActivo")
    private Boolean FlagActivo;
    @SerializedName("Perfil")
    private String Perfil;
    @SerializedName("FlagRestablecer")
    private Boolean FlagRestablecer;
    @SerializedName("FlagPermiso")
    private Boolean FlagPermiso ;
    @SerializedName("Id_Almacen")
    private int Id_Almacen;
    @SerializedName("Almacen")
    private String Almacen;
    @SerializedName("Id_Cliente")
    private Integer Id_Cliente;
    @SerializedName("Cliente")
    private String Cliente;

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getApeNom() {
        return ApeNom;
    }

    public void setApeNom(String apeNom) {
        ApeNom = apeNom;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public byte[] getFoto() {
        return Foto;
    }

    public void setFoto(byte[] foto) {
        Foto = foto;
    }

    public Integer getId_Perfil() {
        return Id_Perfil;
    }

    public void setId_Perfil(Integer id_Perfil) {
        Id_Perfil = id_Perfil;
    }

    public Boolean getFlagActivo() {
        return FlagActivo;
    }

    public void setFlagActivo(Boolean flagActivo) {
        FlagActivo = flagActivo;
    }

    public String getPerfil() {
        return Perfil;
    }

    public void setPerfil(String perfil) {
        Perfil = perfil;
    }

    public Boolean getFlagRestablecer() {
        return FlagRestablecer;
    }

    public void setFlagRestablecer(Boolean flagRestablecer) {
        FlagRestablecer = flagRestablecer;
    }

    public Boolean getFlagPermiso() {
        return FlagPermiso;
    }

    public void setFlagPermiso(Boolean flagPermiso) {
        FlagPermiso = flagPermiso;
    }

    public int getId_Almacen() {
        return Id_Almacen;
    }

    public void setId_Almacen(int id_Almacen) {
        Id_Almacen = id_Almacen;
    }

    public String getAlmacen() {
        return Almacen;
    }

    public void setAlmacen(String almacen) {
        Almacen = almacen;
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
}
