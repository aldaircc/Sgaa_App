package com.example.acosetito.sgaa.ui.Recibo.Tab_05;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;

import java.util.List;

public interface ReciboTab05View {

    void showResultValidatePallet(Mensaje message);
    void showFailureValidatePallet(String result);
    void showResultInsertPallet(String result);
    void showFailureInsertPallet(String result);
    void showResultPrintEtq(Mensaje message);
    void showFailurePrintEtq(String result);
    void showResultRegisterPallet(Mensaje message);
    void showFailureRegisterPallet(String result);
    void getBultos(List<UAXProductoTxA> list);
    void showFailureGetBultos(String result);
    void showResultRegisterUATransito(String result);
    void showFailureRequest(String result);

    void showProgressDialog();
    void hideProgressDialog();
}
