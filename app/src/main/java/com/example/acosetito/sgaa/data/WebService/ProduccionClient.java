package com.example.acosetito.sgaa.data.WebService;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ControlUsuarioPendiente;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProduccionClient {

    @Headers({"Accept:application/json", "Content-Type:application/json"})
    @POST("RegistrarControlOP/strIdOP/intIdLineaMAQ/intIdCausal/strUsuario/strObservacion/bFlagPausa")
    Call<Mensaje> registrarControlOP(
            @Body JsonObject param
            );

    @GET("BuscarControlUsuario")
    Call<ArrayList<ControlUsuarioPendiente>> buscarControlUsuario(
            @Query("strId_OP") String strId_OP,
            @Query("strUsuario") String strUsuario);
}
