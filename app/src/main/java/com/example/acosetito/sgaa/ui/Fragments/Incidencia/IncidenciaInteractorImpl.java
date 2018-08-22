package com.example.acosetito.sgaa.ui.Fragments.Incidencia;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.Causal;
import com.example.acosetito.sgaa.data.Model.Recepcion.ControlPendiente;
import com.example.acosetito.sgaa.data.Model.Recepcion.ControlUsuarioPendiente;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.ApiClient;
import com.example.acosetito.sgaa.data.WebService.ProduccionClient;
import com.example.acosetito.sgaa.data.WebService.ReciboClient;
import com.example.acosetito.sgaa.data.WebService.TablasEstaticasClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncidenciaInteractorImpl implements IncidenciaInteractor{

    @Override
    public void listCausalXModulo(Integer intId_Cliente, Integer intId_Modulo, final OnIncidenciaListener listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.baseUrl, Global.recepcionService).create(ReciboClient.class));
        Call<ArrayList<Causal>> call = reciboClient.listarCausalesXModulo(intId_Cliente, intId_Modulo);
        call.enqueue(new Callback<ArrayList<Causal>>() {
            @Override
            public void onResponse(Call<ArrayList<Causal>> call, Response<ArrayList<Causal>> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessListCausalXModulo(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Causal>> call, Throwable t) {
                listener.OnFailureListCausalXModulo(t.getMessage());
            }
        });
    }

    @Override
    public void registerControlOP(String strIdOP, Integer intId_LineaMAQ, Integer intId_Causal, String strUsuario, String strObservacion, Boolean bolFlagPausa, final OnIncidenciaListener listener) {
        ProduccionClient produccionClient = (ApiClient.getApiClient(Global.baseUrl, Global.produccionService).create(ProduccionClient.class));
        JsonObject param = new JsonObject();
        param.addProperty("strIdOP",strIdOP);
        param.addProperty("intIdLineaMAQ", intId_LineaMAQ);
        param.addProperty("intIdCausal", intId_Causal);
        param.addProperty("strUsuario", strUsuario);
        param.addProperty("strObservacion", strObservacion);
        param.addProperty("bFlagPausa", bolFlagPausa);

        Call<Mensaje> call = produccionClient.registrarControlOP(param);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessRegisterControlOP(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                listener.OnFailureRegisterControlOP(t.getMessage());
            }
        });
    }

    @Override
    public void registerControl(String strId_Tx, Integer intId_Causal, String strUsuario, Integer intId_TerminalRF, String strObservacion, Boolean bolFlagPausa, final OnIncidenciaListener listener) {
        TablasEstaticasClient estaticasClient = (ApiClient.getApiClient(Global.baseUrl, Global.tablasEstaticasService).create(TablasEstaticasClient.class));
        Call<Mensaje> call = estaticasClient.registrarControl(strId_Tx, intId_Causal, strUsuario, intId_TerminalRF, strObservacion, bolFlagPausa);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessRegisterControl(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                listener.OnFailureRegisterControl(t.getMessage());
            }
        });
    }

    @Override
    public void searchControlPendiente(String strId_Tx, String strUsuario, final OnIncidenciaListener listener) {
        TablasEstaticasClient estaticasClient = (ApiClient.getApiClient(Global.baseUrl, Global.tablasEstaticasService).create(TablasEstaticasClient.class));
        Call<ArrayList<ControlPendiente>> call = estaticasClient.buscarControlPendiente(strId_Tx, strUsuario);
        call.enqueue(new Callback<ArrayList<ControlPendiente>>() {
            @Override
            public void onResponse(Call<ArrayList<ControlPendiente>> call, Response<ArrayList<ControlPendiente>> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessSearchControlPendiente(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ControlPendiente>> call, Throwable t) {
                listener.OnFailureSearchControlPendiente(t.getMessage());
            }
        });
    }

    @Override
    public void searchControlUsuario(String strId_OP, String strUsuario, final OnIncidenciaListener listener) {
        ProduccionClient produccionClient = (ApiClient.getApiClient(Global.baseUrl, Global.produccionService).create(ProduccionClient.class));
        Call<ArrayList<ControlUsuarioPendiente>> call = produccionClient.buscarControlUsuario(strId_OP, strUsuario);
        call.enqueue(new Callback<ArrayList<ControlUsuarioPendiente>>() {
            @Override
            public void onResponse(Call<ArrayList<ControlUsuarioPendiente>> call, Response<ArrayList<ControlUsuarioPendiente>> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessSearchControlUsuario(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ControlUsuarioPendiente>> call, Throwable t) {
                listener.OnFailureSearchControlUsuario(t.getMessage());
            }
        });
    }
}
