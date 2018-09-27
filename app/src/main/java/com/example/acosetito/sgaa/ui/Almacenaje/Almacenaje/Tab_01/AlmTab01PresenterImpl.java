package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_01;

import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionTransito;

import java.util.ArrayList;

public class AlmTab01PresenterImpl implements AlmTab01Presenter, AlmTab01Interactor.OnListenerAlmTab01 {

    AlmTab01View view;
    AlmTab01Interactor interactor;

    public AlmTab01PresenterImpl(AlmTab01View view) {
        this.view = view;
        this.interactor = new AlmTab01InteractorImpl();
    }

    @Override
    public void OnSuccessGetUbicacionTransito(ArrayList<UbicacionTransito> list) {
        view.sourceDataUbicacionTransito(list);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureGetUbicacionTransito(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void sourceDataUbicacionTransito(Integer intIdAlmacen) {
        view.showProgressDialog();
        interactor.getUbicationTransito(intIdAlmacen, this);
    }

    @Override
    public void navigateToTab02(String strUbicacion, Integer intIdUbicacion) {
        view.navigateToTab02(strUbicacion, intIdUbicacion);
    }
}
