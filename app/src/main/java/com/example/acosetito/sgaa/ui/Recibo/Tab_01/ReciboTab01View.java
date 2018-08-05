package com.example.acosetito.sgaa.ui.Recibo.Tab_01;

import com.example.acosetito.sgaa.data.Model.Recepcion.ListarRecepcionesXUsuario;

import java.util.List;

public interface ReciboTab01View {
    void sourceDataRecepcionByUser(List<ListarRecepcionesXUsuario> list);
    void showFailureRecepcionByUser(String result);
}
