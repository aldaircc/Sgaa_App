package com.example.acosetito.sgaa.ui.Recibo.Tab_01;

public interface ReciboTab01Presenter {
    void getListarRecepcionByUser(String strUsuario, Integer intIdAlmacen, Integer intIdMuelle);
    void goBackToMenu();
    void navigateToReciboTab02(String strId_Tx, String strNumOrden, Boolean bolFlagPausa, String strCuenta, String strProveedor, Integer intId_TipoMovimiento);
}
