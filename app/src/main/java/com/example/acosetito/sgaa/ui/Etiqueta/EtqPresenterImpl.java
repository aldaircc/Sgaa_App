package com.example.acosetito.sgaa.ui.Etiqueta;

import com.example.acosetito.sgaa.data.Model.Recepcion.SubAlmacenXCuenta;
import com.example.acosetito.sgaa.data.Model.Recepcion.UMxProducto;

import java.util.ArrayList;

public class EtqPresenterImpl implements EtqPresenter, EtqInteractor.OnListenerEtq{

    EtqInteractorImpl interactor;
    EtqView view;

    public EtqPresenterImpl(EtqView view) {
        this.view = view;
        this.interactor = new EtqInteractorImpl();
    }

    @Override
    public void OnSuccessListUMxProducto(ArrayList<UMxProducto> list) {
        view.sourceUMxProducto(list);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureListUMxProducto(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void OnSuccessListSubAlmacenesXCuenta(ArrayList<SubAlmacenXCuenta> list) {
        view.sourceSubAlmacenXCuenta(list);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureListSubAlmacenesXCuenta(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void listUMxProducto(Integer intIdProducto) {
        view.showProgressDialog();
        interactor.listUMxProducto(intIdProducto, this);
    }

    @Override
    public void listSubAlmacenesXCuenta(Integer intIdCuenta, Integer intIdAlmacen) {
        view.showProgressDialog();
        interactor.listSubAlmacenesXCuenta(intIdCuenta, intIdAlmacen,this);
    }
}
