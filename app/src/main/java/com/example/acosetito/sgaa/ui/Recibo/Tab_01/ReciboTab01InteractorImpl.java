package com.example.acosetito.sgaa.ui.Recibo.Tab_01;

import com.example.acosetito.sgaa.data.Model.Recepcion.ListarRecepcionesXUsuario;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.ApiClient;
import com.example.acosetito.sgaa.data.WebService.ReciboClient;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReciboTab01InteractorImpl implements ReciboTab01Interactor {

    @Override
    public void getListarRecepcionByUser(String strUsuario, Integer intIdAlmacen, Integer intIdMuelle, final OnListenerReciboTab01 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.baseUrl, Global.recepcionService)).create(ReciboClient.class);
        Call<List<ListarRecepcionesXUsuario>> call = reciboClient.getRecepcionesXUsuario(strUsuario, intIdAlmacen, intIdMuelle);
        call.enqueue(new Callback<List<ListarRecepcionesXUsuario>>() {
            @Override
            public void onResponse(Call<List<ListarRecepcionesXUsuario>> call, Response<List<ListarRecepcionesXUsuario>> response) {
                switch (response.code()) {
                    case 200:
                        List<ListarRecepcionesXUsuario> list = new ArrayList<>();
                        if (response.body().size() != 0) {
                            list = response.body();
                        }
                        listener.OnSuccessGetListarRecepcionByUser(list);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<ListarRecepcionesXUsuario>> call, Throwable t) {
                listener.OnFailureGetListarRecepcionByUser(t.getMessage());
            }
        });
    }
}