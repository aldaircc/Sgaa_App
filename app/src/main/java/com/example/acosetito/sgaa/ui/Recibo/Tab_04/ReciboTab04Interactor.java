package com.example.acosetito.sgaa.ui.Recibo.Tab_04;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.UA;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;

import java.util.ArrayList;

public interface ReciboTab04Interactor {

    interface OnListenerReciboTab04{
        void OnSuccessRegisterUA(Mensaje message);
        void OnFailureRegisterUA(String result);
        void OnSuccessRegisterUATransferencia(Mensaje message);
        void OnFailureRegisterUATransferencia(String result);
        void OnSuccessGetUAsProductoTx(ArrayList<UAXProductoTxA> list);
        void OnFailureGetUAsProductoTx(String result);
    }

    void getUAsProductoTx(String strIdTx, Integer intIdProducto, Integer intItem, OnListenerReciboTab04 listener);
    void registerUA(UA ua, OnListenerReciboTab04 listener);
    void registerUATransferencia(UA ua, OnListenerReciboTab04 listener);
}
