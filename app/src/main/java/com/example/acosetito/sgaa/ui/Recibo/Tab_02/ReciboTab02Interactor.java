package com.example.acosetito.sgaa.ui.Recibo.Tab_02;

import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;

import java.util.List;

public interface ReciboTab02Interactor {

    interface OnListenerReciboTab02{
        void OnSuccessGetDataDetailTx(List<ListarDetalleTx> list);
        void OnFailureGetDetailTx(String result);
    }

    void getDataDetailTx(String strIdTx, OnListenerReciboTab02 listener);
}
