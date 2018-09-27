package com.example.acosetito.sgaa.data.WebService;

import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionLibreXMarca;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionTransito;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AlmacenajeClient {

    @GET("Total_Items_UbicacionTransito")
    Call<ArrayList<UbicacionTransito>> totalItemsUbicacionTransito(
            @Query("idAlmacen") Integer intIdAlmacen);

    @GET("ValidarUATransito")
    Call<ArrayList<UATransito>> validarUATransito(
            @Query("strUA") String strUA,
            @Query("intIdUbicacion") Integer intIdUbicacion);

    @GET("ListarUbicacionLibreXMarcaSugerida")
    Call<ArrayList<UbicacionLibreXMarca>> listarUbicacionLibreXMarcaSugerida(
        @Query("intIdMarca") Integer intIdMarca,
        @Query("intIdAlmacen") Integer intIdAlmacen,
        @Query("intIdCondicion") Integer intIdCondicion,
        @Query("CodBarraUA") String strCodBarra
    );
}
