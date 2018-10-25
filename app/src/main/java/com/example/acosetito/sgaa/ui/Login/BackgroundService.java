package com.example.acosetito.sgaa.ui.Login;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.acosetito.sgaa.data.Model.Usuario;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.ApiClient;
import com.example.acosetito.sgaa.data.WebService.UserClient;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class BackgroundService extends IntentService {

    public BackgroundService() {
        super("BackgroundService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        UserClient userClient = (ApiClient.getApiClient(Global.baseUrl, Global.usuarioService)).create(UserClient.class);
        Call<ArrayList<Usuario>> call = userClient.validarUsuario(extras.getString("user"), extras.getString("password"));

        try {
            Response<ArrayList<Usuario>> result = call.execute();
            Log.d("Return login", result.body().get(0).getApeNom() + " - " + result.body().get(0).getCorreo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
