package com.example.acosetito.sgaa.ui.Recibo.Tab_04;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
import com.example.acosetito.sgaa.data.Model.Recepcion.UA;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;

import java.util.ArrayList;

public class ReciboTab04PresenterImpl implements ReciboTab04Presenter, ReciboTab04InteractorImpl.OnListenerReciboTab04{

    private ReciboTab04View reciboTab04View;
    private ReciboTab04Interactor interactor;

    public ReciboTab04PresenterImpl(ReciboTab04View view){
        this.reciboTab04View = view;
        this.interactor = new ReciboTab04InteractorImpl();
    }

    @Override
    public void getBultosTx(String strIdTx, Integer intIdProducto, Integer intItem) {
        reciboTab04View.showProgressDialog();
        interactor.getUAsProductoTx(strIdTx, intIdProducto, intItem, this);
    }

    @Override
    public void registerUATransferencia(UA ua) {
        reciboTab04View.showProgressDialog();
        interactor.registerUATransferencia(ua,this);
    }

    @Override
    public void registerUA(UA ua) {
        reciboTab04View.showProgressDialog();
        interactor.registerUA(ua,this);
    }

    @Override
    public void navigateToReciboTab05(ListarDetalleTx ent, String strNroOrden, Integer intTipoMovimiento, Boolean bolAutomatic, Double saldo) {
        reciboTab04View.navigateToReciboTab05(ent, strNroOrden, intTipoMovimiento, bolAutomatic, saldo);
    }

    @Override
    public void navigateToReciboTab03() {
        reciboTab04View.navigateToReciboTab03();
    }

    @Override
    public void OnSuccessRegisterUA(Mensaje message) {
        reciboTab04View.getResultRegisterUA(message);
        reciboTab04View.hideProgressDialog();
    }

    @Override
    public void OnFailureRegisterUA(String result) {
        reciboTab04View.showFailureRegisterUA(result);
        reciboTab04View.hideProgressDialog();
    }

    @Override
    public void OnSuccessRegisterUATransferencia(Mensaje message) {
        reciboTab04View.getResultRegisterUATransferencia(message);
        reciboTab04View.hideProgressDialog();
    }

    @Override
    public void OnFailureRegisterUATransferencia(String result) {
        reciboTab04View.showFailureRegisterUATransferencia(result);
        reciboTab04View.hideProgressDialog();
    }

    @Override
    public void OnSuccessGetUAsProductoTx(ArrayList<UAXProductoTxA> list) {
        reciboTab04View.updateDataSourceBulto(list);
        reciboTab04View.hideProgressDialog();
    }

    @Override
    public void OnFailureGetUAsProductoTx(String result) {
        reciboTab04View.showFailureDataSourceBulto(result);
        reciboTab04View.hideProgressDialog();
    }
}
