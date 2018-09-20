package com.example.acosetito.sgaa.data.WebService;

import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionTransito;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AlmacenajeClient {

    @GET("Total_Items_UbicacionTransito")
    Call<ArrayList<UbicacionTransito>> totalItemsUbicacionTransito(
            @Query("idAlmacen") Integer intIdAlmacen);
}
