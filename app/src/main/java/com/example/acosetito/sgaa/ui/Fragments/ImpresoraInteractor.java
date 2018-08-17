package com.example.acosetito.sgaa.ui.Fragments;

import com.example.acosetito.sgaa.data.Model.Impresora.AccesosImpresoraXUsuario;

import java.util.ArrayList;

public interface ImpresoraInteractor {
    interface OnListenerImpresora{
        void OnSuccessGetListImpresoraXUsuario(ArrayList<AccesosImpresoraXUsuario> list);
        void OnFailureGetListImpresoraXUsuario(String result);
    }
    void getListImpresoraXUsuario(String strUsuario, OnListenerImpresora listener);
}
