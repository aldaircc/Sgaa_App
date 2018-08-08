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
}
