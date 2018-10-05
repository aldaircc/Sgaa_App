package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_03;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;
import com.example.acosetito.sgaa.data.Model.Mensaje;

import java.util.ArrayList;

public interface AlmTab03View {
    void resultRegistrarUAUbicacion(Mensaje message, String strUA);
    void showFailureRequest(String result);
    void navigateToTab02(ArrayList<UATransito> listItemPallets);
    void showProgressDialog();
    void hideProgressDialog();
}
