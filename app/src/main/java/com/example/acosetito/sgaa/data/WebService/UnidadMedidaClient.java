package com.example.acosetito.sgaa.data.WebService;

import com.example.acosetito.sgaa.data.Model.Recepcion.UMxProducto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UnidadMedidaClient {

    @GET("ListarUMXProducto")
    Call<ArrayList<UMxProducto>> listarUMxProducto(
            @Query("intIdProducto") Integer intIdProducto
    );
}
