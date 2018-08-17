package com.example.acosetito.sgaa.ui.Recibo.Tab_05;

import com.example.acosetito.sgaa.data.Model.Mensaje;

public interface ReciboTab05View {

    void showResultValidatePallet(Mensaje message);
    void showFailureValidatePallet(String result);
    void showResultInsertPallet(String result);
    void showFailureInsertPallet(String result);
    void showResultPrintEtq(Mensaje message);
    void showFailurePrintEtq(String result);
    void showProgressDialog();
    void hideProgressDialog();
}
