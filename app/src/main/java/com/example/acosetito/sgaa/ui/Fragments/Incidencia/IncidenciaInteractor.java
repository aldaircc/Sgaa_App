package com.example.acosetito.sgaa.ui.Fragments.Incidencia;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.Causal;
import com.example.acosetito.sgaa.data.Model.Recepcion.ControlPendiente;
import com.example.acosetito.sgaa.data.Model.Recepcion.ControlUsuarioPendiente;

import java.util.ArrayList;

public interface IncidenciaInteractor {

    interface OnIncidenciaListener{
        void OnSuccessListCausalXModulo(ArrayList<Causal> list);
        void OnFailureListCausalXModulo(String result);
        void OnSuccessRegisterControlOP(Mensaje message);
        void OnFailureRegisterControlOP(String result);
        void OnSuccessRegisterControl(Mensaje message);
        void OnFailureRegisterControl(String result);
        void OnSuccessSearchControlPendiente(ArrayList<ControlPendiente> list);
        void OnFailureSearchControlPendiente(String result);
        void OnSuccessSearchControlUsuario(ArrayList<ControlUsuarioPendiente> list);
        void OnFailureSearchControlUsuario(String result);
    }
    void listCausalXModulo(Integer intId_Cliente, Integer intId_Modulo, OnIncidenciaListener listener);
    void registerControlOP(String strIdOP, Integer intId_LineaMAQ, Integer intId_Causal, String strUsuario, String strObservacion, Boolean bolFlagPausa, OnIncidenciaListener listener);
    void registerControl(String strId_Tx, Integer intId_Causal, String strUsuario, Integer intId_TerminalRF, String strObservacion, Boolean bolFlagPausa, OnIncidenciaListener listener);
    void searchControlPendiente(String strId_Tx, String strUsuario, OnIncidenciaListener listener);
    void searchControlUsuario(String strId_OP, String strUsuario, OnIncidenciaListener listener);
}
