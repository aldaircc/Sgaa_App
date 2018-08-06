package com.example.acosetito.sgaa.ui.Recibo.Tab_02;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.ApiClient;
import com.example.acosetito.sgaa.data.WebService.ReciboClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReciboTab02InteractorImpl implements ReciboTab02Interactor{

    @Override
    public void getDataDetailTx(String strIdTx, final OnListenerReciboTab02 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.recepcionService)).create(ReciboClient.class);
        Call<List<ListarDetalleTx>> call = reciboClient.getDetailTx(strIdTx);
        call.enqueue(new Callback<List<ListarDetalleTx>>() {
            @Override
            public void onResponse(Call<List<ListarDetalleTx>> call, Response<List<ListarDetalleTx>> response) {
                switch (response.code()){
                    case 200:
                        List<ListarDetalleTx> list = new ArrayList<>();
                        if (response.body().size() != 0){
                            list = response.body();
                        }
                        listener.OnSuccessGetDataDetailTx(list);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<ListarDetalleTx>> call, Throwable t) {
                listener.OnFailureGetDetailTx(t.getMessage());
            }
        });
    }

    @Override
    public void getCerrarRecepcion(String idTx, Integer idEstado, String usuario, final OnListenerReciboTab02 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.recepcionService)).create(ReciboClient.class);
        JsonObject objParams = new JsonObject();
        objParams.addProperty("idTx", idTx);
        objParams.addProperty("idEstado", idEstado);
        objParams.addProperty("usuario", usuario);
        Call<Mensaje> call = reciboClient.getCerrarRecepcion(objParams);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                switch (response.code()) {
                    case 200:
                        Mensaje message = new Mensaje();
                        message = response.body();
                        listener.OnSuccessGetCerrarRecepcion(message);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                listener.OnFailureGetCerrarRecepcion(t.getMessage());
            }
        });
    }
}