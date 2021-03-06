package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_04;

import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;

import java.util.ArrayList;

public interface AlmTab04Presenter {

    void listarSectoresXAlmacen(Integer intIdAlmacen);
    void listarMasUbicacionDisponibles(Integer intIdAlmacen, Integer intIdMarca, Integer intIdCondicion, Integer intIdSector);
    void listarUbicacionXCodigoBarra(String strUbi, Integer intIdAlmacen);
    void navigateToTab02();
    void navigateToTab03(Integer intId_Condicion, String strCod_Prod, String strProducto,
                         String strCod_Barra, String strCod_UAPallet, String strSector, String strPasillo,
                         String strFila, Integer intId_Ubicacion, Integer intColumna, Integer intNivel,
                         Integer intPosicion, Integer intCountPallets, Integer total, ArrayList<UATransito> lstItemPallets);
}
