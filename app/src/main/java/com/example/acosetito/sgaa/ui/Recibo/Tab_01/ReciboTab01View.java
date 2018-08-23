package com.example.acosetito.sgaa.ui.Recibo.Tab_01;

import com.example.acosetito.sgaa.data.Model.Recepcion.ListarRecepcionesXUsuario;

import java.util.ArrayList;
import java.util.List;

public interface ReciboTab01View {
    void sourceDataRecepcionByUser(ArrayList<ListarRecepcionesXUsuario> list);
    void showFailureRequest(String result);
    void goBackToMenu();
    void showProgressDialog();
    void hideProgressDialog();
}
