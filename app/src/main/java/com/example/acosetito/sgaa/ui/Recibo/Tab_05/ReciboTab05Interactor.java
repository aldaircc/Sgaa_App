package com.example.acosetito.sgaa.ui.Recibo.Tab_05;

import com.example.acosetito.sgaa.data.Model.Impresora.ListaEtiqueta;
import com.example.acosetito.sgaa.data.Model.Mensaje;

import java.util.ArrayList;

public interface ReciboTab05Interactor {

    interface OnListenerReciboTab05{
        void OnSuccessValidatePallet(Mensaje message);
        void OnFailureValidatePallet(String result);
        void OnSuccessPrintListaEtq(Mensaje message);
        void OnFailurePrintListaEtq(String result);
        void OnSuccessInsertPallet(String result);
        void OnFailureInsertPallet(String result);
    }

    void validatePallet(String strPallet, int intIdAlmacen, OnListenerReciboTab05 listener);
    void insertPallet(Integer intId_Almacen, String strUsuario, Integer intId_Centro, OnListenerReciboTab05 listener);
    void printListaEtq(ArrayList<ListaEtiqueta> lista, String strFormato, String strNombreImpresora, Boolean bolEsScript, OnListenerReciboTab05 listener);
}
