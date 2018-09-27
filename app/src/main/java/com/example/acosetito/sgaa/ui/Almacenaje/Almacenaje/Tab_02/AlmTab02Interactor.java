package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_02;

import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;

import java.util.ArrayList;

public interface AlmTab02Interactor {
    interface OnListenerAlmTab02{
        void OnSuccessValidateUATransito(ArrayList<UATransito> list);
        void OnFailureValidateUATransito(String result);
    }

    void validateUATransito(String strUA, Integer intIdUbicacion, OnListenerAlmTab02 listener);
}