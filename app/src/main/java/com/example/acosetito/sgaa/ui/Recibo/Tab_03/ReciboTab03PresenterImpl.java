package com.example.acosetito.sgaa.ui.Recibo.Tab_03;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.TxUbicacion;
import com.example.acosetito.sgaa.data.Model.Recepcion.UA;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;

import java.util.List;

public class ReciboTab03PresenterImpl implements ReciboTab03Presenter, ReciboTab03Interactor.OnListenerReciboTab03{

    private ReciboTab03View reciboTab03View;
    private ReciboTab03Interactor interactor;

    public ReciboTab03PresenterImpl(ReciboTab03View view){
        this.reciboTab03View = view;
        this.interactor = new ReciboTab03InteractorImpl();
    }

    @Override
    public void getUAsProductoTx(String strIdTx, Integer intIdProducto, Integer intItem) {
        this.interactor.getUAsProductoTx(strIdTx, intIdProducto, intItem, this);
    }

    @Override
    public void OnSuccessGetUAsProductoTx(List<UAXProductoTxA> list) {
        reciboTab03View.sourceDataUAsProducto(list);
    }

    @Override
    public void OnFailureGetUAsProductoTx(String result) {
        reciboTab03View.showFailureUAXProduco(result);
    }

    @Override
    public void validateReciboTransferSerie(String strNumOrden, String strSerie, Integer intItem) {
        interactor.validateReciboTransferSerie(strNumOrden, strSerie, intItem, this);
    }

    @Override
    public void OnSuccessValidateReciboTransferSerie(Mensaje message) {
        reciboTab03View.showResultValidateReciboTransferSerie(message);
    }

    @Override
    public void OnFailureValidateReciboTransferSerie(String result) {
        reciboTab03View.showFailureValidateReciboTransferSerie(result);
    }

    @Override
    public void validateReciboSerie(String strSerie, String strIdTx, Integer intIdProducto) {
        interactor.validateReciboSerie(strSerie, strIdTx, intIdProducto, this);
    }

    @Override
    public void OnSucessValidateReciboSerie(Mensaje message) {
        reciboTab03View.showResultValidateReciboSerie(message);
    }

    @Override
    public void OnFailureValidateReciboSerie(String result) {
        reciboTab03View.showFailureValidateReciboSerie(result);
    }

    @Override
    public void validateEmptyBarCode(String barCode) {
        interactor.validateEmptyBarCode(barCode, this);
    }

    @Override
    public void OnSuccessValidateEmptyBarCode(String result) {
        reciboTab03View.showMessageValidationBarCode(result);
    }

    @Override
    public void OnFailureValidateEmptyBarCode(String result) {
        reciboTab03View.showMessageValidationBarCode(result);
    }

    @Override
    public void validateUAReciboTransferencia(UA ua) {
        interactor.validateUAReciboTransferencia(ua, this);
    }

    @Override
    public void OnSuccessValidateUAReciboTransferencia(Mensaje message) {
        reciboTab03View.showResultValidateReciboTransferSerie(message);
    }

    @Override
    public void OnFailureValidateUAReciboTransferencia(String result) {
        reciboTab03View.showFailureValidateUAReciboTransferencia(result);
    }

    @Override
    public void validateUARecibo(UA ua) {
        interactor.validateUARecibo(ua, this);
    }

    @Override
    public void OnSuccessValidateUARecibo(Mensaje message) {
        reciboTab03View.showResultValidateUARecibo(message);
    }

    @Override
    public void OnFailureValidateUARecibo(String result) {
        reciboTab03View.showFailureValidateUARecibo(result);
    }

    @Override
    public void registerUATransito(TxUbicacion txUbi) {
        interactor.registerUATransito(txUbi, this);
    }

    @Override
    public void OnSuccessRegisterUATransito(String result) {
        reciboTab03View.showResultRegistrarUATransito(result);
    }

    @Override
    public void OnFailureRegisterUATransito(String result) {
        reciboTab03View.showFailureRegistrarUATransito(result);
    }

    @Override
    public void registerUA(UA ua) {
        interactor.registerUA(ua,this);
    }

    @Override
    public void OnSuccessRegisterUA(Mensaje message) {
        reciboTab03View.showResultRegisterUA(message);
    }

    @Override
    public void OnFailureRegisterUA(String result) {
        reciboTab03View.showFailureRegisterUA(result);
    }

    @Override
    public void registerUATransferencia(UA ua) {
        interactor.registerUATransferencia(ua, this);
    }

    @Override
    public void OnSuccessRegisterUATransferencia(Mensaje message) {
        reciboTab03View.showResultRegisterUATransferencia(message);
    }

    @Override
    public void OnFailureRegisterUASTransferencia(String result) {
        reciboTab03View.showFailureRegisterUATransferencia(result);
    }
}
