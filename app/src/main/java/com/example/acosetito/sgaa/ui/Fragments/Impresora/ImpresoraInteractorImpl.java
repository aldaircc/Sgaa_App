package com.example.acosetito.sgaa.ui.Fragments.Impresora;
import com.example.acosetito.sgaa.data.Model.Impresora.AccesosImpresoraXUsuario;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.ApiClient;
import com.example.acosetito.sgaa.data.WebService.UserClient;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImpresoraInteractorImpl implements ImpresoraInteractor{
    @Override
    public void getListImpresoraXUsuario(String strUsuario, final OnListenerImpresora listener) {
        UserClient userClient = (ApiClient.getApiClient(Global.baseUrl, Global.usuarioService)).create(UserClient.class);
        Call<ArrayList<AccesosImpresoraXUsuario>> call = userClient.listarAccesosImpresoraXUsuario(strUsuario);
        call.enqueue(new Callback<ArrayList<AccesosImpresoraXUsuario>>() {
            @Override
            public void onResponse(Call<ArrayList<AccesosImpresoraXUsuario>> call, Response<ArrayList<AccesosImpresoraXUsuario>> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessGetListImpresoraXUsuario(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AccesosImpresoraXUsuario>> call, Throwable t) {
                listener.OnFailureGetListImpresoraXUsuario(t.getMessage());
            }
        });
    }
}
