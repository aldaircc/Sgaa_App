package com.example.acosetito.sgaa.ui.Recibo.Tab_01;

import com.example.acosetito.sgaa.data.Model.Recepcion.ListarRecepcionesXUsuario;

import java.util.List;

public class ReciboTab01PresenterImpl implements ReciboTab01Presenter, ReciboTab01Interactor.OnListenerReciboTab01 {

    private ReciboTab01View view;
    private ReciboTab01Interactor interactor;

    public ReciboTab01PresenterImpl(ReciboTab01View view){
        this.view = view;
        this.interactor = new ReciboTab01InteractorImpl();
    }

    @Override
    public void getListarRecepcionByUser(String strUsuario, Integer intIdAlmacen, Integer intIdMuelle) {
        interactor.getListarRecepcionByUser(strUsuario, intIdAlmacen, intIdMuelle, this);
    }

    @Override
    public void OnSuccessGetListarRecepcionByUser(List<ListarRecepcionesXUsuario> list) {
            view.sourceDataRecepcionByUser(list);
    }

    @Override
    public void OnFailureGetListarRecepcionByUser(String result) {
            view.showFailureRecepcionByUser(result);
    }
}
