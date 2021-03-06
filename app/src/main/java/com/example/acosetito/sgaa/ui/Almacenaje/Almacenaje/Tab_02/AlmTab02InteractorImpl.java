package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_02;

import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionLibreXMarca;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.AlmacenajeClient;
import com.example.acosetito.sgaa.data.WebService.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlmTab02InteractorImpl implements AlmTab02Interactor{

    @Override
    public void validateUATransito(String strUA, Integer intIdUbicacion, final OnListenerAlmTab02 listener) {
        AlmacenajeClient almacenajeClient = (ApiClient.getApiClient(Global.baseUrl, Global.almacenajeService).create(AlmacenajeClient.class));
        Call<ArrayList<UATransito>> call = almacenajeClient.validarUATransito(strUA, intIdUbicacion);
        call.enqueue(new Callback<ArrayList<UATransito>>() {
            @Override
            public void onResponse(Call<ArrayList<UATransito>> call, Response<ArrayList<UATransito>> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessValidateUATransito(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UATransito>> call, Throwable t) {
                listener.OnFailureValidateUATransito(t.getMessage());
            }
        });
    }

    @Override
    public void listarUbicacionLibrexMarcaSugerida(Integer intId_Marca, Integer intId_Almacen, Integer intId_Condicion, String strCodUA_Pallet, final OnListenerAlmTab02 listener) {
        AlmacenajeClient almacenajeClient = (ApiClient.getApiClient(Global.baseUrl, Global.almacenajeService).create(AlmacenajeClient.class));
        Call<ArrayList<UbicacionLibreXMarca>> call = almacenajeClient.listarUbicacionLibreXMarcaSugerida(intId_Marca, intId_Almacen, intId_Condicion, strCodUA_Pallet);
        call.enqueue(new Callback<ArrayList<UbicacionLibreXMarca>>() {
            @Override
            public void onResponse(Call<ArrayList<UbicacionLibreXMarca>> call, Response<ArrayList<UbicacionLibreXMarca>> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessListarUbicacionLibrexMarcaSugerida(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UbicacionLibreXMarca>> call, Throwable t) {
                listener.OnFailureListarUbicacionLibrexMarcaSugerida(t.getMessage());
            }
        });
    }

}
