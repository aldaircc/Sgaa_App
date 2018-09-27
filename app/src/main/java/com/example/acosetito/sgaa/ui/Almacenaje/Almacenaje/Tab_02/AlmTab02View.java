package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_02;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionLibreXMarca;

import java.util.ArrayList;

public interface AlmTab02View {
    void resultValidateUA(ArrayList<UATransito> list);
    void resultListarUbicacionLibrexMarcaSugerida(ArrayList<UbicacionLibreXMarca> list);
    void showFailureRequest(String result);
    void navigateToTab01();
    void navigateToTab03(Integer intId_Marca, Integer intId_Condicion, String strCod_Prod, String strProducto,
                         String strCod_Barra, String strCod_UAPallet, String strSector, String strPasillo,
                         String strFila, Integer intId_Ubicacion, Integer intColumna, Integer intNivel,
                         String strPosicion, Integer intCountPallets, Integer total);
    void navigateToTab04();
    void showProgressDialog();
    void hideProgressDialog();
}