package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_04;

import com.example.acosetito.sgaa.data.Model.Almacenaje.SectorXAlmacen;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionDisponible;

import java.util.ArrayList;

public interface AlmTab04Interactor {

    interface OnListenerAlmTab04{
        void OnSuccessListarSectoresXAlmacen(ArrayList<SectorXAlmacen> list);
        void OnFailureListarSectoresXAlmacen(String result);
        void OnSuccessListarMasUbicacionesDisponibles(ArrayList<UbicacionDisponible> list);
        void OnFailureListarMasUbicacionesDisponibles(String result);
    }

    void listarSectoresXAlmacen(Integer intIdAlmacen, OnListenerAlmTab04 listener);
    void listarMasUbicacionesDisponibles(Integer intIdAlmacen, Integer intIdMarca, Integer intIdCondicion, Integer intIdSector, OnListenerAlmTab04 listener);
}
