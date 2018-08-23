package com.example.acosetito.sgaa.ui.Recibo.Tab_02;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
import java.util.ArrayList;

public interface ReciboTab02Interactor {

    interface OnListenerReciboTab02{
        void OnSuccessGetCerrarRecepcion(Mensaje message);
        void OnFailureGetCerrarRecepcion(String result);
        void OnSuccessGetDataDetailTx(ArrayList<ListarDetalleTx> list);
        void OnFailureGetDetailTx(String result);
    }

    void getDataDetailTx(String strIdTx, OnListenerReciboTab02 listener);
    void getCerrarRecepcion(String idTx, Integer idEstado, String usuario, OnListenerReciboTab02 listener);
}
