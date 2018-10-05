package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_03;

import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;

import java.util.ArrayList;

public interface AlmTab03Presenter {

    void registrarUAUbicacion(String strUA, Integer intIdUbicacion, String strUsuario);
    void navigateToTab02(ArrayList<UATransito> listItemPallets);
}
