package com.example.acosetito.sgaa.ui.Recibo.Tab_02;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;

import java.util.List;

public interface ReciboTab02View {
    void showResultCerrarRecepcion(Mensaje message);
    void showFailureCerrarRecepcion(String result);
    void sourceDataDetailTx(List<ListarDetalleTx> list);
    void showFailureDetailTx(String result);
}
