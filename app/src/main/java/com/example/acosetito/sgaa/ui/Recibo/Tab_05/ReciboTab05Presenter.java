package com.example.acosetito.sgaa.ui.Recibo.Tab_05;

import com.example.acosetito.sgaa.data.Model.Impresora.ListaEtiqueta;

import java.util.ArrayList;

public interface ReciboTab05Presenter {

    void validatePallet(String strPallet, int intIdAlmacen);
    void insertPallet(Integer intId_Almacen, String strUsuario, Integer intId_Centro);
    void printListaEtq(ArrayList<ListaEtiqueta> lista, String strFormato, String strNombreImpresora, Boolean bolEsScript);
}
