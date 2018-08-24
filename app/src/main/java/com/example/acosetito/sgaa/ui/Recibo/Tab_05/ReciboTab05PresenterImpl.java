package com.example.acosetito.sgaa.ui.Recibo.Tab_05;

import android.os.Handler;

import com.example.acosetito.sgaa.data.Model.Impresora.ListaEtiqueta;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ImpUA;
import com.example.acosetito.sgaa.data.Model.Recepcion.TxUbicacion;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;

import java.util.ArrayList;
import java.util.List;

public class ReciboTab05PresenterImpl implements ReciboTab05Presenter, ReciboTab05Interactor.OnListenerReciboTab05{

    private ReciboTab05Interactor interactor;
    private ReciboTab05View view;

    public ReciboTab05PresenterImpl(ReciboTab05View view){
        this.view = view;
        this.interactor = new ReciboTab05InteractorImpl();
    }

    @Override
    public void validatePallet(String strPallet, int intIdAlmacen) {
        view.showProgressDialog();
        interactor.validatePallet(strPallet, intIdAlmacen, this);
    }

    @Override
    public void OnSuccessValidatePallet(Mensaje message) {
        view.showResultValidatePallet(message);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureValidatePallet(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void insertPallet(Integer intId_Almacen, String strUsuario, Integer intId_Centro) {
        view.showProgressDialog();
        interactor.insertPallet(intId_Almacen, strUsuario, intId_Centro, this);
    }

    @Override
    public void OnSuccessInsertPallet(String result) {
        view.showResultInsertPallet(result);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureInsertPallet(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void registerPallet(ArrayList<ImpUA> ua) {
        view.showProgressDialog();
        interactor.registerPallet(ua, this);
    }

    @Override
    public void OnSuccessRegisterPallet(Mensaje message) {
        view.showResultRegisterPallet(message);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureRegisterPallet(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void printListaEtq(ArrayList<ListaEtiqueta> lista, String strFormato, String strNombreImpresora, Boolean bolEsScript) {
        view.showProgressDialog();
        interactor.printListaEtq(lista, strFormato, strNombreImpresora, bolEsScript,this);
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
    public void getUAsProductoTx(String strIdTx, Integer intIdProducto, Integer intItem) {
        view.showProgressDialog();
        interactor.getUAsProductoTx(strIdTx, intIdProducto, intItem, this);
    }

    @Override
    public void registerUATransito(TxUbicacion txUbi) {
        view.showProgressDialog();
        interactor.registerUATransito(txUbi, this);
    }

    @Override
    public void navigateToReciboTab04() {
        view.navigateToReciboTab04();
    }

    @Override
    public void OnSuccessGetUAsProductoTx(List<UAXProductoTxA> list) {
        view.getBultos(list);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureGetUAsProductoTx(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void OnSuccessRegisterUATransito(String result) {
        view.showResultRegisterUATransito(result);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureRegisterUATransito(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }
}
