package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_03;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;
import com.example.acosetito.sgaa.data.Model.Mensaje;

import java.util.ArrayList;

public class AlmTab03PresenterImpl implements AlmTab03Presenter, AlmTab03Interactor.OnListenerAlmTab03{

    private AlmTab03View view;
    private AlmTab03Interactor interactor;

    public AlmTab03PresenterImpl(AlmTab03View view) {
        this.view = view;
        this.interactor = new AlmTab03InteractorImpl();
    }

    @Override
    public void OnSuccessRegistrarUAUbicacion(Mensaje message, String strUA) {
        view.resultRegistrarUAUbicacion(message, strUA);
        //view.hideProgressDialog();
    }

    @Override
    public void OnFailureRegistrarUAUbicacion(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void registrarUAUbicacion(String strUA, Integer intIdUbicacion, String strUsuario) {
        //view.showProgressDialog();
        interactor.registrarUAUbicacion(strUA, intIdUbicacion, strUsuario, this);
    }

    @Override
    public void navigateToTab02(ArrayList<UATransito> listItemPallets) {
        view.navigateToTab02(listItemPallets);
    }
}
