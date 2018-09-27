package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_02;

import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;

import java.util.ArrayList;

public class AlmTab02PresenterImpl implements AlmTab02Presenter, AlmTab02Interactor.OnListenerAlmTab02{

    private AlmTab02View view;
    private AlmTab02Interactor interactor;

    public AlmTab02PresenterImpl(AlmTab02View view) {
        this.view = view;
        this.interactor = new AlmTab02InteractorImpl();
    }

    @Override
    public void OnSuccessValidateUATransito(ArrayList<UATransito> list) {
        view.resultValidateUA(list);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureValidateUATransito(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void validateUATransito(String strUA, Integer intIdUbicacion) {
        view.showProgressDialog();
        interactor.validateUATransito(strUA, intIdUbicacion, this);
    }

    @Override
    public void navigateToTab01() {
        view.navigateToTab01();
    }
}
