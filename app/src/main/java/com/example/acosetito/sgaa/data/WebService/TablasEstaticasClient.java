package com.example.acosetito.sgaa.data.WebService;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ControlPendiente;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TablasEstaticasClient {

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("RegistrarControl")
    //@POST("RegistrarControl?Id_Tx={Id_Tx}&Id_Causal={Id_Causal}&Usuario={Usuario}&Id_TerminalRF={Id_TerminalRF}&Observacion={Observacion}&FlagPausa={FlagPausa}")
    Call<Mensaje> registrarControl(
            @Query("Id_Tx") String strId_Tx,
            @Query("Id_Causal") Integer intId_Causal,
            @Query("Usuario") String strUsuario,
            @Query("Id_TerminalRF") Integer intId_TerminalRF,
            @Query("Observacion") String strObservacion,
            @Query("FlagPausa") Boolean bolFlagPausa
    );

    @GET("BuscarControlPendiente")
    Call<ArrayList<ControlPendiente>> buscarControlPendiente(
            @Query("StrIdTx") String strId_Tx,
            @Query("strUsuario") String strUsuario
    );
}
