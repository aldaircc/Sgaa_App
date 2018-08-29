package com.example.acosetito.sgaa.ui.Etiqueta;

import com.example.acosetito.sgaa.data.Model.Impresora.ListaEtiqueta;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ImpUA;
import com.example.acosetito.sgaa.data.Model.Recepcion.SubAlmacenXCuenta;
import com.example.acosetito.sgaa.data.Model.Recepcion.UMxProducto;

import java.util.ArrayList;
import java.util.HashMap;

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
    public void OnSuccessRegisterUAMasivo(ArrayList<ImpUA> list) {
        view.showResultRegisterMasivo(list);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureRegisterUAMasivo(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void OnSuccessPrintListaEtq(Mensaje message) {
        view.showResultPrintEtq(message);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailurePrintListaEtq(String result) {
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

    @Override
    public void listLabelsFormat(String[] list) {
        view.showProgressDialog();
        view.sourceLabelsFormat(list);
        view.hideProgressDialog();
    }

    @Override
    public void showDialogImpresora() {
        view.showProgressDialog();
        view.showDialogImpresora();
        view.hideProgressDialog();
    }

    @Override
    public void registerUAMasivo(ImpUA imp, Integer intId_Centro) {
        view.showProgressDialog();
        interactor.registerUAMasivo(imp, intId_Centro, this);
    }

    @Override
    public void printListaEtq(ArrayList<ListaEtiqueta> lista, String strFormato, String strNombreImpresora, Boolean bolEsScript) {
        view.showProgressDialog();
        interactor.printListaEtq(lista, strFormato, strNombreImpresora, bolEsScript, this);
    }
}
