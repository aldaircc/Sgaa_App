package com.example.acosetito.sgaa.ui.Recibo.Tab_03;

public interface ReciboTab03Presenter {

    void getUAsProductoTx(String strIdTx, Integer intIdProducto, Integer intItem);
    void validateReciboTransferSerie(String strNumOrden, String strSerie, Integer intItem);
    void validateReciboSerie(String strSerie, String strIdTx, Integer intIdProducto);
    void validateEmptyBarCode(String barCode);
}
