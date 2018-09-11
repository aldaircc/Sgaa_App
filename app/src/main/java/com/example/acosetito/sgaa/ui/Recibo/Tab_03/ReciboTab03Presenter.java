package com.example.acosetito.sgaa.ui.Recibo.Tab_03;

import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
import com.example.acosetito.sgaa.data.Model.Recepcion.TxUbicacion;
import com.example.acosetito.sgaa.data.Model.Recepcion.UA;

public interface ReciboTab03Presenter {

    void getUAsProductoTx(String strIdTx, Integer intIdProducto, Integer intItem);
    void validateReciboTransferSerie(String strNumOrden, String strSerie, Integer intItem);
    void validateReciboSerie(String strSerie, String strIdTx, Integer intIdProducto);
    void validateEmptyBarCode(String barCode);
    void validateUAReciboTransferencia(UA ua);
    void validateUARecibo(UA ua);
    void registerUATransito(TxUbicacion txUbi);
    void registerUA(UA ua);
    void registerUATransferencia(UA ua);
    void showDialogImpresora();
    void navigateToEtqCajaLpn(ListarDetalleTx detail, String strCuenta, String strNroOrden, Integer intId_Cliente, Integer intId_TipoMovimiento, Integer intId_CuentaLPN);
    void navigateToReciboTab04(ListarDetalleTx ent, String strNroOrden, Integer intTipoMovimiento, Boolean bolAuto, Double currentSaldo);
    void navigateToReciboTab02();
}