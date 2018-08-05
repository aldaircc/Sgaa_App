package com.example.acosetito.sgaa.data.Adapter.Interfaces;

import android.view.View;

import com.example.acosetito.sgaa.data.Model.Recepcion.ListarRecepcionesXUsuario;

public interface IRVReciboTab01Adapter {
    void onSelectItem(ListarRecepcionesXUsuario ent);
    void onItemClick(View v, int pos);
}
