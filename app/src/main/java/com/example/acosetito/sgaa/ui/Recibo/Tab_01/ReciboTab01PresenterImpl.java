package com.example.acosetito.sgaa.ui.Recibo.Tab_01;

import com.example.acosetito.sgaa.data.Model.Recepcion.ListarRecepcionesXUsuario;

import java.util.ArrayList;
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
        view.showProgressDialog();
        interactor.getListarRecepcionByUser(strUsuario, intIdAlmacen, intIdMuelle, this);
    }

    @Override
    public void goBackToMenu() {
        view.goBackToMenu();
    }

    @Override
    public void OnSuccessGetListarRecepcionByUser(ArrayList<ListarRecepcionesXUsuario> list) {
            view.sourceDataRecepcionByUser(list);
            view.hideProgressDialog();
    }

    @Override
    public void OnFailureGetListarRecepcionByUser(String result) {
            view.showFailureRequest(result);
            view.hideProgressDialog();
    }
}
