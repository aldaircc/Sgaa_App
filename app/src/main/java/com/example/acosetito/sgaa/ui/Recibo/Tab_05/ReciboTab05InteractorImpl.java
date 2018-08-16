package com.example.acosetito.sgaa.ui.Recibo.Tab_05;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.ApiClient;
import com.example.acosetito.sgaa.data.WebService.ReciboClient;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReciboTab05InteractorImpl implements ReciboTab05Interactor{
    @Override
    public void validatePallet(String strPallet, int intIdAlmacen, final OnListenerReciboTab05 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.recepcionService)).create(ReciboClient.class);
        JsonObject obj = new JsonObject();
        obj.addProperty("strPallet", strPallet);
        obj.addProperty("IdAlmacen", intIdAlmacen);
        Call<Mensaje> call = reciboClient.validarPallet(obj);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                switch (response.code()){
                    case 200:
                        Mensaje message = response.body();
                        listener.OnSuccessValidatePallet(message);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                listener.OnFailureValidatePallet(t.getMessage());
            }
        });
    }
}
