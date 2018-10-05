package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_03;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.AlmacenajeClient;
import com.example.acosetito.sgaa.data.WebService.ApiClient;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlmTab03InteractorImpl implements AlmTab03Interactor {
    @Override
    public void registrarUAUbicacion(final String strUA, final Integer intIdUbicacion, String strUsuario, final OnListenerAlmTab03 listener) {

        AlmacenajeClient almClient = (ApiClient.getApiClient(Global.baseUrl, Global.almacenajeService).create(AlmacenajeClient.class));
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("strUA", strUA);
        obj.put("intIdUbicacion", intIdUbicacion);
        obj.put("strUsuario", strUsuario);
        Call<Mensaje> call = almClient.registrarUAUbicacion(obj);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                switch (response.code()){
                    case 200 :
                        listener.OnSuccessRegistrarUAUbicacion(response.body(), strUA);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                listener.OnFailureRegistrarUAUbicacion(t.getMessage());
            }
        });
    }
}
