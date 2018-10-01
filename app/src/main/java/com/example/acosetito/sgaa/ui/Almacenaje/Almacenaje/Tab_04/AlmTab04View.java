package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_04;
import com.example.acosetito.sgaa.data.Model.Almacenaje.SectorXAlmacen;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionDisponible;

import java.util.ArrayList;

public interface AlmTab04View {

    void resultListarSector(ArrayList<SectorXAlmacen> list);
    void resultListarMasUbicacionesDisponibles(ArrayList<UbicacionDisponible> list);
    void navigateToTab03();
    void showFailureRequest(String result);
    void showProgressDialog();
    void hideProgressDialog();
}
