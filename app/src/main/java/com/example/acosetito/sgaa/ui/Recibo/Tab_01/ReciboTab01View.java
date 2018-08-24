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
    void navigateToReciboTab02(String strId_Tx, String strNumOrden, Boolean bolFlagPausa, String strCuenta, String strProveedor, Integer intId_TipoMovimiento);
}
