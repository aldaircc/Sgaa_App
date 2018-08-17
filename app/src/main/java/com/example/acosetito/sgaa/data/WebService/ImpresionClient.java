package com.example.acosetito.sgaa.data.WebService;

import com.example.acosetito.sgaa.data.Model.Mensaje;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ImpresionClient {

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("imprimirListaEtiquetas/lista/formato/nombreImpresora/esScript")
    Call<Mensaje> imprimirListaEtiquetas(
            @Body HashMap<String, Object> obj
    );
}
