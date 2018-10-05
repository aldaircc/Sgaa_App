package com.example.acosetito.sgaa.data.Model.Almacenaje;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class UATransito implements Parcelable{
    @SerializedName("UA_CodBarra")
    public String UA_CodBarra;
    @SerializedName("Id_Producto")
    public Integer Id_Producto;
    @SerializedName("CodigoProducto")
    public String CodigoProducto;
    @SerializedName("NombreProducto")
    public String NombreProducto;
    @SerializedName("Id_UM")
    public Integer Id_UM;
    @SerializedName("UM")
    public String UM;
    @SerializedName("Cantidad")
    public Double Cantidad;
    @SerializedName("LoteLab")
    public String LoteLab;
    @SerializedName("LotePT")
    public String LotePT;
    @SerializedName("FlagDisponible")
    public Boolean FlagDisponible;
    @SerializedName("FlagAveriado")
    public Boolean FlagAveriado;
    @SerializedName("Id_Marca")
    public Integer Id_Marca;
    @SerializedName("FlagCuarentena")
    public Boolean FlagCuarentena;
    @SerializedName("Id_Condicion")
    public Integer Id_Condicion;
    @SerializedName("FechaEmision")
    public Date FechaEmision;
    @SerializedName("FechaVencimiento")
    public Date FechaVencimiento;
    @SerializedName("Serie")
    public String Serie;
    @SerializedName("IsComplete")
    public Boolean isComplete;

    public UATransito() {

    }

    public String getUA_CodBarra() {
        return UA_CodBarra;
    }

    public void setUA_CodBarra(String UA_CodBarra) {
        this.UA_CodBarra = UA_CodBarra;
    }

    public Integer getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(Integer id_Producto) {
        Id_Producto = id_Producto;
    }

    public String getCodigoProducto() {
        return CodigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        CodigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }

    public Integer getId_UM() {
        return Id_UM;
    }

    public void setId_UM(Integer id_UM) {
        Id_UM = id_UM;
    }

    public String getUM() {
        return UM;
    }

    public void setUM(String UM) {
        this.UM = UM;
    }

    public Double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Double cantidad) {
        Cantidad = cantidad;
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

    public Integer getId_Marca() {
        return Id_Marca;
    }

    public void setId_Marca(Integer id_Marca) {
        Id_Marca = id_Marca;
    }

    public Boolean getFlagCuarentena() {
        return FlagCuarentena;
    }

    public void setFlagCuarentena(Boolean flagCuarentena) {
        FlagCuarentena = flagCuarentena;
    }

    public Integer getId_Condicion() {
        return Id_Condicion;
    }

    public void setId_Condicion(Integer id_Condicion) {
        Id_Condicion = id_Condicion;
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

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String serie) {
        Serie = serie;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public UATransito(Parcel parcel){
        UA_CodBarra = parcel.readString();
        Id_Producto = parcel.readInt();
        CodigoProducto = parcel.readString();
        NombreProducto = parcel.readString();
        Id_UM = parcel.readInt();
        UM = parcel.readString();
        Cantidad = parcel.readDouble();
        LoteLab = parcel.readString();
        LotePT = parcel.readString();
        FlagDisponible = (Boolean)parcel.readValue(null);
        FlagAveriado = (Boolean)parcel.readValue(null);
        Id_Marca = parcel.readInt();
        FlagCuarentena = (Boolean)parcel.readValue(null);
        Id_Condicion = parcel.readInt();
        FechaEmision = (Date)parcel.readValue(null);
        FechaVencimiento = (Date)parcel.readValue(null);
        Serie = parcel.readString();
        isComplete = (Boolean)parcel.readValue(null);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(UA_CodBarra);
        dest.writeInt(Id_Producto);
        dest.writeString(CodigoProducto);
        dest.writeString(NombreProducto);
        dest.writeInt(Id_UM);
        dest.writeString(UM);
        dest.writeDouble(Cantidad);
        dest.writeString(LoteLab);
        dest.writeString(LotePT);
        dest.writeValue(FlagDisponible);
        dest.writeValue(FlagAveriado);
        dest.writeInt(Id_Marca);
        dest.writeValue(FlagCuarentena);
        dest.writeInt(Id_Condicion);
        dest.writeValue(FechaEmision);
        dest.writeValue(FechaVencimiento);
        dest.writeString(Serie);
        dest.writeValue(isComplete);
    }

    //used when un-parceling our parcel (creating the object)
    public static final Parcelable.Creator<UATransito> CREATOR = new Parcelable.Creator<UATransito>(){

        @Override
        public UATransito createFromParcel(Parcel parcel) {
            return new UATransito(parcel);
        }

        @Override
        public UATransito[] newArray(int size) {
            return new UATransito[0];
        }
    };

}
