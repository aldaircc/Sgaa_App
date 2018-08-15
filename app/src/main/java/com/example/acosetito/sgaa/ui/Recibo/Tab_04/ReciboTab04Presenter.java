package com.example.acosetito.sgaa.ui.Recibo.Tab_04;

import com.example.acosetito.sgaa.data.Model.Recepcion.UA;

public interface ReciboTab04Presenter {
    void getBultosTx(String strIdTx, Integer intIdProducto, Integer intItem);
    void registerUATransferencia(UA ua);
    void registerUA(UA ua);
}
