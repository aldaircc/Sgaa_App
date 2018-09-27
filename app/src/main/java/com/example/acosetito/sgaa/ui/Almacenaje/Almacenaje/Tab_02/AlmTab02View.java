package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_02;

import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;

import java.util.ArrayList;

public interface AlmTab02View {
    void resultValidateUA(ArrayList<UATransito> list);
    void showFailureRequest(String result);
    void navigateToTab01();
    void showProgressDialog();
    void hideProgressDialog();
}
