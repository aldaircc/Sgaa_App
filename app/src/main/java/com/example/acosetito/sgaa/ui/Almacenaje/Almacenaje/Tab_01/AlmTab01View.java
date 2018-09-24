package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_01;

import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionTransito;

import java.util.ArrayList;

public interface AlmTab01View {
    void sourceDataUbicacionTransito(ArrayList<UbicacionTransito> list);
    void showFailureRequest(String result);
    void navigateToTab02();
    void showProgressDialog();
    void hideProgressDialog();
}
