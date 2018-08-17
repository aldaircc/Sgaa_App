package com.example.acosetito.sgaa.ui.Fragments;

import com.example.acosetito.sgaa.data.Model.Impresora.AccesosImpresoraXUsuario;

import java.util.ArrayList;

public interface ImpresoraView {
    void sourceImpresora(ArrayList<AccesosImpresoraXUsuario> list);
    void showFailureImpresora(String result);
    void showProgressDialog();
    void hideProgressDialog();
}
