package com.example.acosetito.sgaa.ui.Recibo.Tab_02;

import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;

import java.util.List;

public interface ReciboTab02Presenter {

    void getDataDetailTx(String strIdTx);
    void getCerrarRecepcion(String idTx, Integer idEstado, String usuario);
    void showDialogImpresora();
    void showDialogIncidencia(String strId_Tx, String strNumOrden, Boolean bolFlagPausa, String strCuenta, String strProveedor, Integer intId_TipoMovimiento);
    void navigateToReciboTab01();
    void navigateToReciboTab03(ListarDetalleTx ent, String strNumOrden, Integer intIdTipoMovimiento, Boolean bolAutomatic, Boolean bolFlagPausa);
    void navigateToEtqCajaLpn();
}
