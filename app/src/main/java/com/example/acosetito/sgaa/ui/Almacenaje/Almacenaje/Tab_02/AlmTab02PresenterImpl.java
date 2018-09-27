package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_02;

import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionLibreXMarca;

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
    public void OnSuccessListarUbicacionLibrexMarcaSugerida(ArrayList<UbicacionLibreXMarca> list) {
        view.resultListarUbicacionLibrexMarcaSugerida(list);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureListarUbicacionLibrexMarcaSugerida(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void validateUATransito(String strUA, Integer intIdUbicacion) {
        view.showProgressDialog();
        interactor.validateUATransito(strUA, intIdUbicacion, this);
    }

    @Override
    public void listarUbicacionLibrexMarcaSugerida(Integer intId_Marca, Integer intId_Almacen, Integer intId_Condicion, String strCodUA_Pallet) {
        view.showProgressDialog();
        interactor.listarUbicacionLibrexMarcaSugerida(intId_Marca, intId_Almacen, intId_Condicion, strCodUA_Pallet, this);
    }

    @Override
    public void navigateToTab01() {
        view.navigateToTab01();
    }

    @Override
    public void navigateToTab03(Integer intId_Marca, Integer intId_Condicion, String strCod_Prod, String strProducto, String strCod_Barra, String strCod_UAPallet, String strSector, String strPasillo, String strFila, Integer intId_Ubicacion, Integer intColumna, Integer intNivel, String strPosicion, Integer intCountPallets, Integer total) {
        view.navigateToTab03(intId_Marca, intId_Condicion, strCod_Prod, strProducto, strCod_Barra,
                             strCod_UAPallet, strSector, strPasillo, strFila, intId_Ubicacion,
                             intColumna, intNivel, strPosicion, intCountPallets, total);
    }

    @Override
    public void navigateToTab04() {
        view.navigateToTab04();
    }

}
