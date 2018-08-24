package com.example.acosetito.sgaa.ui.Recibo.Tab_05;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;

import java.util.List;

public interface ReciboTab05View {

    void showResultValidatePallet(Mensaje message);
    void showResultInsertPallet(String result);
    void showResultPrintEtq(Mensaje message);
    void showResultRegisterPallet(Mensaje message);
    void getBultos(List<UAXProductoTxA> list);
    void showResultRegisterUATransito(String result);
    void showFailureRequest(String result);
    void navigateToReciboTab04();
    void showProgressDialog();
    void hideProgressDialog();

}
