package com.example.acosetito.sgaa.ui.Recibo.Tab_01;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarRecepcionesXUsuario;

import java.util.ArrayList;
import java.util.List;

public interface ReciboTab01Interactor {
    interface OnListenerReciboTab01{
        void OnSuccessGetListarRecepcionByUser(ArrayList<ListarRecepcionesXUsuario> list);
        void OnFailureGetListarRecepcionByUser(String result);
    }

    void getListarRecepcionByUser(String strUsuario, Integer intIdAlmacen, Integer intIdMuelle, OnListenerReciboTab01 listener);
}
