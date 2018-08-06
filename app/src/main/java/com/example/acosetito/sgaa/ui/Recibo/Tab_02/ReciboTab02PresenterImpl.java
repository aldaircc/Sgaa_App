package com.example.acosetito.sgaa.ui.Recibo.Tab_02;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;

import java.util.List;

public class ReciboTab02PresenterImpl implements ReciboTab02Presenter, ReciboTab02Interactor.OnListenerReciboTab02{

    private ReciboTab02View view;
    private ReciboTab02Interactor interactor;

    public ReciboTab02PresenterImpl(ReciboTab02View view){
        this.view = view;
        this.interactor = new ReciboTab02InteractorImpl();
    }

    @Override
    public void getCerrarRecepcion(String idTx, Integer idEstado, String usuario) {
        interactor.getCerrarRecepcion(idTx, idEstado, usuario, this);
    }

    @Override
    public void OnSuccessGetCerrarRecepcion(Mensaje message) {
        view.showResultCerrarRecepcion(message);
    }

    @Override
    public void OnFailureGetCerrarRecepcion(String result) {
        view.showFailureCerrarRecepcion(result);
    }

    @Override
    public void OnSuccessGetDataDetailTx(List<ListarDetalleTx> list) {
        view.sourceDataDetailTx(list);
    }

    @Override
    public void OnFailureGetDetailTx(String result) {
        view.showFailureDetailTx(result);
    }

    @Override
    public void getDataDetailTx(String strIdTx) {
        interactor.getDataDetailTx(strIdTx, this);
    }

}
