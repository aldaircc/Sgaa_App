package com.example.acosetito.sgaa.data.WebService;
import com.example.acosetito.sgaa.data.Model.ListarTransferenciaSubAlmacenXUsuario;
import com.google.gson.JsonObject;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TransferenciaClient {

    @Headers({"Accept: application/json",
            "Content-Type:application/json"})
    @POST("ListarTransferenciaSubAlmacenXUsuario/strUsuario/intIdAlmacen")
    Call<List<ListarTransferenciaSubAlmacenXUsuario>> getTransfSubAlmacenByUser(
            @Body JsonObject data);

}
