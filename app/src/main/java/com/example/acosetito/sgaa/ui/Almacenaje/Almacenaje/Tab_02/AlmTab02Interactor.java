package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_02;

import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionLibreXMarca;

import java.util.ArrayList;

public interface AlmTab02Interactor {
    interface OnListenerAlmTab02{
        void OnSuccessValidateUATransito(ArrayList<UATransito> list);
        void OnFailureValidateUATransito(String result);
        void OnSuccessListarUbicacionLibrexMarcaSugerida(ArrayList<UbicacionLibreXMarca> list);
        void OnFailureListarUbicacionLibrexMarcaSugerida(String result);
    }

    void validateUATransito(String strUA, Integer intIdUbicacion, OnListenerAlmTab02 listener);
    void listarUbicacionLibrexMarcaSugerida(Integer intId_Marca, Integer intId_Almacen, Integer intId_Condicion,
                                            String strCodUA_Pallet, OnListenerAlmTab02 listener);
}