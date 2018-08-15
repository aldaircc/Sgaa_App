package com.example.acosetito.sgaa.ui.Recibo.Tab_04;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;

import java.util.List;

public interface ReciboTab04View {
    void updateDataSourceBulto(List<UAXProductoTxA> list);
    void showFailureDataSourceBulto(String result);
    void getResultRegisterUATransferencia(Mensaje message);
    void showFailureRegisterUATransferencia(String result);
    void getResultRegisterUA(Mensaje message);
    void showFailureRegisterUA(String result);
}
