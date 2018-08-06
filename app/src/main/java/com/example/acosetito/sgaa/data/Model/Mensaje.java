package com.example.acosetito.sgaa.data.Model;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

public class Mensaje {

    @SerializedName("errNumber")
    public Integer errNumber;
    @SerializedName("message")
    public String message;
    @SerializedName("valor1")
    public Float valor1;
    @SerializedName("valor2")
    public Float valor2;

    public Integer getErrNumber() {
        return errNumber;
    }

    public void setErrNumber(Integer errNumber) {
        this.errNumber = errNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Float getValor1() {
        return valor1;
    }

    public void setValor1(Float valor1) {
        this.valor1 = valor1;
    }

    public Float getValor2() {
        return valor2;
    }

    public void setValor2(Float valor2) {
        this.valor2 = valor2;
    }
}
