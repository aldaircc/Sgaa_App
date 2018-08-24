package com.example.acosetito.sgaa.ui.Recibo.Tab_03;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
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
        reciboTab03View.showProgressDialog();
        this.interactor.getUAsProductoTx(strIdTx, intIdProducto, intItem, this);
    }

    @Override
    public void OnSuccessGetUAsProductoTx(List<UAXProductoTxA> list) {
        reciboTab03View.sourceDataUAsProducto(list);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void OnFailureGetUAsProductoTx(String result) {
        reciboTab03View.showFailureRequest(result);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void validateReciboTransferSerie(String strNumOrden, String strSerie, Integer intItem) {
        reciboTab03View.showProgressDialog();
        interactor.validateReciboTransferSerie(strNumOrden, strSerie, intItem, this);
    }

    @Override
    public void OnSuccessValidateReciboTransferSerie(Mensaje message) {
        reciboTab03View.showResultValidateReciboTransferSerie(message);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void OnFailureValidateReciboTransferSerie(String result) {
        reciboTab03View.showFailureRequest(result);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void validateReciboSerie(String strSerie, String strIdTx, Integer intIdProducto) {
        reciboTab03View.showProgressDialog();
        interactor.validateReciboSerie(strSerie, strIdTx, intIdProducto, this);
    }

    @Override
    public void OnSucessValidateReciboSerie(Mensaje message) {
        reciboTab03View.showResultValidateReciboSerie(message);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void OnFailureValidateReciboSerie(String result) {
        reciboTab03View.showFailureRequest(result);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void validateEmptyBarCode(String barCode) {
        reciboTab03View.showProgressDialog();
        interactor.validateEmptyBarCode(barCode, this);
    }

    @Override
    public void OnSuccessValidateEmptyBarCode(String result) {
        reciboTab03View.showMessageValidationBarCode(result);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void OnFailureValidateEmptyBarCode(String result) {
        reciboTab03View.showMessageValidationBarCode(result);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void validateUAReciboTransferencia(UA ua) {
        reciboTab03View.showProgressDialog();
        interactor.validateUAReciboTransferencia(ua, this);
    }

    @Override
    public void OnSuccessValidateUAReciboTransferencia(Mensaje message) {
        reciboTab03View.showResultValidarUAReciboTransferencia(message);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void OnFailureValidateUAReciboTransferencia(String result) {
        reciboTab03View.showFailureRequest(result);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void validateUARecibo(UA ua) {
        reciboTab03View.showProgressDialog();
        interactor.validateUARecibo(ua, this);
    }

    @Override
    public void OnSuccessValidateUARecibo(Mensaje message) {
        reciboTab03View.showResultValidateUARecibo(message);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void OnFailureValidateUARecibo(String result) {
        reciboTab03View.showFailureRequest(result);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void registerUATransito(TxUbicacion txUbi) {
        reciboTab03View.showProgressDialog();
        interactor.registerUATransito(txUbi, this);
    }

    @Override
    public void OnSuccessRegisterUATransito(String result) {
        reciboTab03View.showResultRegistrarUATransito(result);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void OnFailureRegisterUATransito(String result) {
        reciboTab03View.showFailureRequest(result);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void registerUA(UA ua) {
        reciboTab03View.showProgressDialog();
        interactor.registerUA(ua,this);
    }

    @Override
    public void OnSuccessRegisterUA(Mensaje message) {
        reciboTab03View.showResultRegisterUA(message);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void OnFailureRegisterUA(String result) {
        reciboTab03View.showFailureRequest(result);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void registerUATransferencia(UA ua) {
        reciboTab03View.showProgressDialog();
        interactor.registerUATransferencia(ua, this);
    }

    @Override
    public void navigateToReciboTab04(ListarDetalleTx ent, String strNroOrden, Integer intTipoMovimiento, Boolean bolAuto, Double currentSaldo) {
        reciboTab03View.navigateToReciboTab04(ent, strNroOrden, intTipoMovimiento, bolAuto, currentSaldo);
    }

    @Override
    public void navigateToReciboTab02() {
        reciboTab03View.navigateToReciboTab02();
    }

    @Override
    public void OnSuccessRegisterUATransferencia(Mensaje message) {
        reciboTab03View.showResultRegisterUATransferencia(message);
        reciboTab03View.hideProgressDialog();
    }

    @Override
    public void OnFailureRegisterUASTransferencia(String result) {
        reciboTab03View.showFailureRequest(result);
        reciboTab03View.hideProgressDialog();
    }
}
