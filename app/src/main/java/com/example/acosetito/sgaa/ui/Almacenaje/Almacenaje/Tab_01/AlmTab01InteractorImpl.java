package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_01;

import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionTransito;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.AlmacenajeClient;
import com.example.acosetito.sgaa.data.WebService.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlmTab01InteractorImpl implements AlmTab01Interactor {
    @Override
    public void getUbicationTransito(Integer intIdAlmacen, final OnListenerAlmTab01 listener) {
        AlmacenajeClient almacenajeClient = (ApiClient.getApiClient(Global.baseUrl, Global.almacenajeService).create(AlmacenajeClient.class));
        Call<ArrayList<UbicacionTransito>> call = almacenajeClient.totalItemsUbicacionTransito(intIdAlmacen);
        call.enqueue(new Callback<ArrayList<UbicacionTransito>>() {
            @Override
            public void onResponse(Call<ArrayList<UbicacionTransito>> call, Response<ArrayList<UbicacionTransito>> response) {
                switch (response.code()){
                    case 200:
                            listener.OnSuccessGetUbicacionTransito(response.body());
                        break;
                    default:
                        break;
                };
            }

            @Override
            public void onFailure(Call<ArrayList<UbicacionTransito>> call, Throwable t) {
                listener.OnFailureGetUbicacionTransito(t.getMessage());
            }
        });
    }
}
