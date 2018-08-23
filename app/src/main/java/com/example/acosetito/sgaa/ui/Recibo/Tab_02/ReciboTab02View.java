package com.example.acosetito.sgaa.ui.Recibo.Tab_02;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
import java.util.ArrayList;

public interface ReciboTab02View {
    void showResultCerrarRecepcion(Mensaje message);
    void sourceDataDetailTx(ArrayList<ListarDetalleTx> list);
    void showFailureRequest(String result);
    void showDialogImpresora();
    void showDialogIncidencia(String strId_Tx, String strNumOrden,
                              Boolean bolFlagPausa, String strCuenta,
                              String strProveedor, Integer intId_TipoMovimiento);
    void navigateToReciboTab01();
    void showProgressDialog();
    void hideProgressDialog();
}
