package com.example.acosetito.sgaa.ui.Fragments.Incidencia;

public interface IncidenciaPresenter {

    void listCausalXModulo(Integer intId_Cliente, Integer intId_Modulo);
    void registerControlOP(String strIdOP, Integer intId_LineaMAQ, Integer intId_Causal, String strUsuario, String strObservacion, Boolean bolFlagPausa);
    void registerControl(String strId_Tx, Integer intId_Causal, String strUsuario, Integer intId_TerminalRF, String strObservacion, Boolean bolFlagPausa);
    void searchControlPendiente(String strId_Tx, String strUsuario);
    void searchControlUsuario(String strId_OP, String strUsuario);
}
