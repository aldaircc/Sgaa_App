package com.example.acosetito.sgaa.ui.Recibo.Tab_03;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;

import java.util.List;

public interface ReciboTab03Interactor {

    interface OnListenerReciboTab03{
        void OnSuccessGetUAsProductoTx(List<UAXProductoTxA> list);
        void OnFailureGetUAsProductoTx(String result);
        void OnSuccessValidateReciboTransferSerie(Mensaje message);
        void OnFailureValidateReciboTransferSerie(String result);
        void OnSucessValidateReciboSerie(Mensaje message);
        void OnFailureValidateReciboSerie(String result);
        void OnSuccessValidateEmptyBarCode(String result);
        void OnFailureValidateEmptyBarCode(String result);
    }

    void getUAsProductoTx(String strIdTx, Integer intIdProducto, Integer intItem, OnListenerReciboTab03 listener);
    void validateReciboTransferSerie(String strNumOrden, String strSerie, Integer intItem, OnListenerReciboTab03 listener);
    void validateReciboSerie(String strSerie, String strIdTx, Integer intIdProducto, OnListenerReciboTab03 listener);
    void validateEmptyBarCode(String barCode, OnListenerReciboTab03 listener);
}
