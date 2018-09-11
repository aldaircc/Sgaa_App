package com.example.acosetito.sgaa.ui.Recibo.Tab_03;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;

import java.util.List;

public interface ReciboTab03View {
    void sourceDataUAsProducto(List<UAXProductoTxA> list);
    void showResultValidateReciboTransferSerie(Mensaje message);
    void showResultValidateReciboSerie(Mensaje message);
    void showMessageValidationBarCode(String result);
    void showResultValidarUAReciboTransferencia(Mensaje message);
    void showResultValidateUARecibo(Mensaje message);
    void showResultRegistrarUATransito(String result);
    void showResultRegisterUA(Mensaje message);
    void showResultRegisterUATransferencia(Mensaje message);
    void showFailureRequest(String result);
    void showDialogImpresora();
    void navigateToReciboTab04(ListarDetalleTx ent, String strNroOrden, Integer intTipoMovimiento, Boolean bolAuto, Double currentSaldo);
    void navigateToReciboTab02();
    void navigateToEtqCajaLpn(ListarDetalleTx detail, String strCuenta, String strNroOrden, Integer intId_Cliente, Integer intId_TipoMovimiento, Integer intId_CuentaLPN);
    void showProgressDialog();
    void hideProgressDialog();
}
