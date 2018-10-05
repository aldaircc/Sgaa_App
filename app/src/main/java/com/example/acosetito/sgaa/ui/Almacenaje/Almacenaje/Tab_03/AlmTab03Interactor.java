package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_03;

import com.example.acosetito.sgaa.data.Model.Mensaje;

public interface AlmTab03Interactor {

    interface OnListenerAlmTab03{
        void OnSuccessRegistrarUAUbicacion(Mensaje message, String strCod_Barra);
        void OnFailureRegistrarUAUbicacion(String result);
    }

    void registrarUAUbicacion(String strUA, Integer intIdUbicacion, String strUsuario, OnListenerAlmTab03 listener);
}
