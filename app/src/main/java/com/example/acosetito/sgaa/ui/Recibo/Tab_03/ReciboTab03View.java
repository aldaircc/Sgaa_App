package com.example.acosetito.sgaa.ui.Recibo.Tab_03;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;

import java.util.List;

public interface ReciboTab03View {
    void sourceDataUAsProducto(List<UAXProductoTxA> list);
    void showFailureUAXProduco(String result);

    void showResultValidateReciboTransferSerie(Mensaje message);
    void showFailureValidateReciboTransferSerie(String result);

    void showResultValidateReciboSerie(Mensaje message);
    void showFailureValidateReciboSerie(String result);

    void showMessageValidationBarCode(String result);

    void showResultValidarUAReciboTransferencia(Mensaje message);
    void showFailureValidateUAReciboTransferencia(String result);

    void showResultValidateUARecibo(Mensaje message);
    void showFailureValidateUARecibo(String result);

    void showResultRegistrarUATransito(String result);
    void showFailureRegistrarUATransito(String result);

    void showResultRegisterUA(Mensaje message);
    void showFailureRegisterUA(String result);

    void showResultRegisterUATransferencia(Mensaje message);
    void showFailureRegisterUATransferencia(String result);

    void navigatoToBulto();
}
