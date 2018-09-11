package com.example.acosetito.sgaa.ui.Recibo.Tab_02;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
import com.example.acosetito.sgaa.data.Model.Recepcion.UA;
import com.example.acosetito.sgaa.data.Model.Usuario;

import java.util.ArrayList;
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
        view.showProgressDialog();
        interactor.getCerrarRecepcion(idTx, idEstado, usuario, this);
    }

    @Override
    public void showDialogImpresora() {
        view.showDialogImpresora();
    }

    @Override
    public void showDialogIncidencia(String strId_Tx, String strNumOrden, Boolean bolFlagPausa, String strCuenta, String strProveedor, Integer intId_TipoMovimiento) {
        view.showDialogIncidencia(strId_Tx, strNumOrden, bolFlagPausa, strCuenta, strProveedor, intId_TipoMovimiento);
    }

    @Override
    public void navigateToReciboTab01() {
        view.navigateToReciboTab01();
    }

    @Override
    public void navigateToReciboTab03(ListarDetalleTx ent, String strNumOrden, Integer intIdTipoMovimiento, Boolean bolAutomatic, Boolean bolFlagPausa, String strCuenta, Integer intId_Cliente) {
        view.navigateToReciboTab03(ent, strNumOrden, intIdTipoMovimiento, bolAutomatic, bolFlagPausa, strCuenta, intId_Cliente);
    }

    @Override
    public void navigateToEtqCajaLpn(ListarDetalleTx detail, String strCuenta, String strNroOrden, Integer intId_Cliente, Integer intId_TipoMovimiento, Integer intId_CuentaLPN) {
        view.navigateToEtqCajaLpn(detail, strCuenta, strNroOrden, intId_Cliente, intId_TipoMovimiento, intId_CuentaLPN);
    }

    @Override
    public void OnSuccessGetCerrarRecepcion(Mensaje message) {
        view.showResultCerrarRecepcion(message);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureGetCerrarRecepcion(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void OnSuccessGetDataDetailTx(ArrayList<ListarDetalleTx> list) {
        view.sourceDataDetailTx(list);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureGetDetailTx(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void getDataDetailTx(String strIdTx) {
        view.showProgressDialog();
        interactor.getDataDetailTx(strIdTx, this);
    }

}
