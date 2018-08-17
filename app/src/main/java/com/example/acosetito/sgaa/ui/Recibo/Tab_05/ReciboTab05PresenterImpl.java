package com.example.acosetito.sgaa.ui.Recibo.Tab_05;

import android.os.Handler;

import com.example.acosetito.sgaa.data.Model.Impresora.ListaEtiqueta;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;

import java.util.ArrayList;

public class ReciboTab05PresenterImpl implements ReciboTab05Presenter, ReciboTab05Interactor.OnListenerReciboTab05{

    private ReciboTab05Interactor interactor;
    private ReciboTab05View view;

    public ReciboTab05PresenterImpl(ReciboTab05View view){
        this.view = view;
        this.interactor = new ReciboTab05InteractorImpl();
    }

    @Override
    public void validatePallet(String strPallet, int intIdAlmacen) {
        /**new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.showProgressDialog();
            }
        }, 500);**/
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
        view.showFailureValidatePallet(result);
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
        view.showFailureInsertPallet(result);
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
        view.showFailurePrintEtq(result);
        view.hideProgressDialog();
    }
}
