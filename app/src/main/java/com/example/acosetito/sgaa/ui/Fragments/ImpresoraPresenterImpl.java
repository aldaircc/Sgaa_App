package com.example.acosetito.sgaa.ui.Fragments;

import com.example.acosetito.sgaa.data.Model.Impresora.AccesosImpresoraXUsuario;

import java.util.ArrayList;

public class ImpresoraPresenterImpl implements ImpresoraPresenter, ImpresoraInteractor.OnListenerImpresora {

    private ImpresoraView view;
    private ImpresoraInteractor interactor;

    public ImpresoraPresenterImpl(ImpresoraView impresoraView) {
        this.view = impresoraView;
        this.interactor = new ImpresoraInteractorImpl();
    }

    @Override
    public void getListImpresoraXUsuario(String strUsuario) {
        view.showProgressDialog();
        interactor.getListImpresoraXUsuario(strUsuario, this);
    }

    @Override
    public void OnSuccessGetListImpresoraXUsuario(ArrayList<AccesosImpresoraXUsuario> list) {
        view.sourceImpresora(list);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureGetListImpresoraXUsuario(String result) {
        view.showFailureImpresora(result);
        view.hideProgressDialog();
    }
}
