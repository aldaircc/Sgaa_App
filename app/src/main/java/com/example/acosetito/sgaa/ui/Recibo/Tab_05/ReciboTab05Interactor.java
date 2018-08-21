package com.example.acosetito.sgaa.ui.Recibo.Tab_05;

import com.example.acosetito.sgaa.data.Model.Impresora.ListaEtiqueta;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ImpUA;
import com.example.acosetito.sgaa.data.Model.Recepcion.TxUbicacion;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;

import java.util.ArrayList;
import java.util.List;

public interface ReciboTab05Interactor {

    interface OnListenerReciboTab05{
        void OnSuccessValidatePallet(Mensaje message);
        void OnFailureValidatePallet(String result);
        void OnSuccessPrintListaEtq(Mensaje message);
        void OnFailurePrintListaEtq(String result);
        void OnSuccessInsertPallet(String result);
        void OnFailureInsertPallet(String result);
        void OnSuccessRegisterPallet(Mensaje message);
        void OnFailureRegisterPallet(String result);
        void OnSuccessGetUAsProductoTx(List<UAXProductoTxA> list);
        void OnFailureGetUAsProductoTx(String result);
        void OnSuccessRegisterUATransito(String result);
        void OnFailureRegisterUATransito(String result);
    }

    void validatePallet(String strPallet, int intIdAlmacen, OnListenerReciboTab05 listener);
    void insertPallet(Integer intId_Almacen, String strUsuario, Integer intId_Centro, OnListenerReciboTab05 listener);
    void printListaEtq(ArrayList<ListaEtiqueta> lista, String strFormato, String strNombreImpresora, Boolean bolEsScript, OnListenerReciboTab05 listener);
    void registerPallet(ArrayList<ImpUA> ua, OnListenerReciboTab05 listener);
    void getUAsProductoTx(String strIdTx, Integer intIdProducto, Integer intItem, final OnListenerReciboTab05 listener);
    void registerUATransito(TxUbicacion txUbi, OnListenerReciboTab05 listener);
}
