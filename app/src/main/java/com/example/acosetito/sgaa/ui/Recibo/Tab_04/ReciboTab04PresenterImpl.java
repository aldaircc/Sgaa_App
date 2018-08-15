package com.example.acosetito.sgaa.ui.Recibo.Tab_04;

import com.example.acosetito.sgaa.data.Model.Mensaje;
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
        interactor.getUAsProductoTx(strIdTx, intIdProducto, intItem, this);
    }

    @Override
    public void registerUATransferencia(UA ua) {
        interactor.registerUATransferencia(ua,this);
    }

    @Override
    public void registerUA(UA ua) {
        interactor.registerUA(ua,this);
    }

    @Override
    public void OnSuccessRegisterUA(Mensaje message) {
        reciboTab04View.getResultRegisterUA(message);
    }

    @Override
    public void OnFailureRegisterUA(String result) {
        reciboTab04View.showFailureRegisterUA(result);
    }

    @Override
    public void OnSuccessRegisterUATransferencia(Mensaje message) {
        reciboTab04View.getResultRegisterUATransferencia(message);
    }

    @Override
    public void OnFailureRegisterUATransferencia(String result) {
        reciboTab04View.showFailureRegisterUATransferencia(result);
    }

    @Override
    public void OnSuccessGetUAsProductoTx(ArrayList<UAXProductoTxA> list) {
        reciboTab04View.updateDataSourceBulto(list);
    }

    @Override
    public void OnFailureGetUAsProductoTx(String result) {
        reciboTab04View.showFailureDataSourceBulto(result);
    }
}
