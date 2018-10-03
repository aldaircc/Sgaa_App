package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_04;
import com.example.acosetito.sgaa.data.Model.Almacenaje.SectorXAlmacen;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionDisponible;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionXCodigoBarra;

import java.util.ArrayList;

public interface AlmTab04View {

    void resultListarSector(ArrayList<SectorXAlmacen> list);
    void resultListarMasUbicacionesDisponibles(ArrayList<UbicacionDisponible> list);
    void resultListarUbicacionXCodigoBarra(ArrayList<UbicacionXCodigoBarra> list);
    void navigateToTab03(Integer intId_Condicion, String strCod_Prod, String strProducto,
                         String strCod_Barra, String strCod_UAPallet, String strSector, String strPasillo,
                         String strFila, Integer intId_Ubicacion, Integer intColumna, Integer intNivel,
                         Integer intPosicion, Integer intCountPallets, Integer total);
    void showFailureRequest(String result);
    void showProgressDialog();
    void hideProgressDialog();
}
