package com.example.acosetito.sgaa.ui.Recibo.Tab_05;

import com.example.acosetito.sgaa.data.Model.Mensaje;

public interface ReciboTab05Interactor {

    interface OnListenerReciboTab05{
        void OnSuccessValidatePallet(Mensaje message);
        void OnFailureValidatePallet(String result);
    }

    void validatePallet(String strPallet, int intIdAlmacen, OnListenerReciboTab05 listener);
}
