package com.example.acosetito.sgaa.data.WebService;
import com.example.acosetito.sgaa.data.Model.Almacenaje.SectorXAlmacen;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionDisponible;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionLibreXMarca;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionTransito;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionXCodigoBarra;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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

    @GET("ListarSectoresXAlmacen")
    Call<ArrayList<SectorXAlmacen>> listarSectoresXAlmacen(
        @Query("intIdAlmacen") Integer intIdAlmacen
    );

    @GET("ListarMasUbicacionesDisponibles")
    Call<ArrayList<UbicacionDisponible>> listarMasUbicacionesDisponibles(
            @Query("intIdAlmacen") Integer intIdAlmacen,
            @Query("intIdMarca") Integer intIdMarca,
            @Query("intIdCondicion") Integer intIdCondicion,
            @Query("intIdSector") Integer intIdSector
    );

    @GET("ListarUbicacionXCodigoBarra")
    Call<ArrayList<UbicacionXCodigoBarra>> listarUbicacionXCodigoBarra(
            @Query("strUbi") String strUbi,
            @Query("intIdAlmacen") Integer intIdAlmacen
    );

    @Headers({"Accept: application/json","Content-Type: application/json"})
    @POST("RegistrarUAUbicacion/strUA/intIdUbicacion/strUsuario")
    Call<Mensaje> registrarUAUbicacion(
            @Body HashMap<String, Object> obj
    );
}
