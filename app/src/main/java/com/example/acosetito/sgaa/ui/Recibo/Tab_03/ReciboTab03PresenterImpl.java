package com.example.acosetito.sgaa.ui.Recibo.Tab_03;

import com.example.acosetito.sgaa.data.Model.Mensaje;
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
}
