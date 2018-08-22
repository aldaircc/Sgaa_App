package com.example.acosetito.sgaa.ui.Fragments.Incidencia;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.Causal;
import com.example.acosetito.sgaa.data.Model.Recepcion.ControlPendiente;
import com.example.acosetito.sgaa.data.Model.Recepcion.ControlUsuarioPendiente;

import java.util.ArrayList;

public interface IncidenciaView {

    void showResultListarCausalXModulo(ArrayList<Causal> list);
    void showFailureRequest(String result);
    void showResultRegisterControlOP(Mensaje message);
    void showResultRegisterControl(Mensaje message);
    void showResultSearchControlPendiente(ArrayList<ControlPendiente> list);
    void showResultSearchControlUsuario(ArrayList<ControlUsuarioPendiente> list);
    void showProgressDialog();
    void hideProgressDialog();
}
