package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_01;

import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionTransito;

import java.util.ArrayList;

public interface AlmTab01Interactor {
    interface OnListenerAlmTab01{
        void OnSuccessGetUbicacionTransito(ArrayList<UbicacionTransito> list);
        void OnFailureGetUbicacionTransito(String result);
    }
    void getUbicationTransito(Integer intIdAlmacen, OnListenerAlmTab01 listener);
}
