package com.example.acosetito.sgaa.ui.Fragments.Incidencia;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.Causal;
import com.example.acosetito.sgaa.data.Model.Recepcion.ControlPendiente;
import com.example.acosetito.sgaa.data.Model.Recepcion.ControlUsuarioPendiente;

import java.util.ArrayList;

public class IncidenciaPresenterImpl implements IncidenciaPresenter, IncidenciaInteractor.OnIncidenciaListener {

    private IncidenciaView view;
    private IncidenciaInteractor interactor;

    public IncidenciaPresenterImpl(IncidenciaView view) {
        this.view = view;
        this.interactor = new IncidenciaInteractorImpl();
    }

    @Override
    public void OnSuccessListCausalXModulo(ArrayList<Causal> list) {
        view.showResultListarCausalXModulo(list);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureListCausalXModulo(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void OnSuccessRegisterControlOP(Mensaje message) {
        view.showResultRegisterControlOP(message);
    }

    @Override
    public void OnFailureRegisterControlOP(String result) {
        view.showFailureRequest(result);
    }

    @Override
    public void OnSuccessRegisterControl(Mensaje message) {
        view.showResultRegisterControl(message);
    }

    @Override
    public void OnFailureRegisterControl(String result) {
        view.showFailureRequest(result);
    }

    @Override
    public void OnSuccessSearchControlPendiente(ArrayList<ControlPendiente> list) {
        view.showResultSearchControlPendiente(list);
    }

    @Override
    public void OnFailureSearchControlPendiente(String result) {
        view.showFailureRequest(result);
    }

    @Override
    public void OnSuccessSearchControlUsuario(ArrayList<ControlUsuarioPendiente> list) {
        view.showResultSearchControlUsuario(list);
    }

    @Override
    public void OnFailureSearchControlUsuario(String result) {
        view.showFailureRequest(result);
    }

    @Override
    public void listCausalXModulo(Integer intId_Cliente, Integer intId_Modulo) {
        interactor.listCausalXModulo(intId_Cliente, intId_Modulo,this);
    }

    @Override
    public void registerControlOP(String strIdOP, Integer intId_LineaMAQ, Integer intId_Causal, String strUsuario, String strObservacion, Boolean bolFlagPausa) {
        interactor.registerControlOP(strIdOP, intId_LineaMAQ, intId_Causal, strUsuario, strObservacion, bolFlagPausa, this);
    }

    @Override
    public void registerControl(String strId_Tx, Integer intId_Causal, String strUsuario, Integer intId_TerminalRF, String strObservacion, Boolean bolFlagPausa) {
        interactor.registerControl(strId_Tx, intId_Causal, strUsuario, intId_TerminalRF, strObservacion, bolFlagPausa, this);
    }

    @Override
    public void searchControlPendiente(String strId_Tx, String strUsuario) {
        interactor.searchControlPendiente(strId_Tx, strUsuario,this);
    }

    @Override
    public void searchControlUsuario(String strId_OP, String strUsuario) {
        interactor.searchControlUsuario(strId_OP, strUsuario, this);
    }
}
