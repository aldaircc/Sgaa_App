package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_02;

public interface AlmTab02Presenter {
    void validateUATransito(String strUA, Integer intIdUbicacion);
    void listarUbicacionLibrexMarcaSugerida(Integer intId_Marca, Integer intId_Almacen, Integer intId_Condicion, String strCodUA_Pallet);
    void navigateToTab01();
    void navigateToTab03(Integer intId_Marca, Integer intId_Condicion, String strCod_Prod, String strProducto,
                         String strCod_Barra, String strCod_UAPallet, String strSector, String strPasillo,
                         String strFila, Integer intId_Ubicacion, Integer intColumna, Integer intNivel,
                         String strPosicion, Integer intCountPallets, Integer total);
    void navigateToTab04(Integer intId_Marca, Integer intTotal_RowsUbi, Integer intId_Condicion, String strCod_Producto, String strProducto);
}
