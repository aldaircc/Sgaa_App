package com.example.acosetito.sgaa.data.WebService;

import com.example.acosetito.sgaa.data.Model.Almacen;
import com.example.acosetito.sgaa.data.Model.CentroDistribucion;
import com.example.acosetito.sgaa.data.Model.Impresora.AccesosImpresoraXUsuario;
import com.example.acosetito.sgaa.data.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserClient {
    @GET("ValidarUsuarioAndroid")
    Call<List<Usuario>> validateUserAndroid(
            @Query("Usuario") String user,
            @Query("Clave") String password,
            @Query("idTerminal") Integer idTerminal);

    @GET("ListarCentrosXUsuario")
    Call<List<CentroDistribucion>> getCenterByUser(
            @Query("strUsuario") String strUsuario
    );

    @GET("ListarAlmacenesXUsuario")
    Call<List<Almacen>> getWarehouseByUser(
            @Query("strUsuario") String strUsuario,
            @Query("intIdCentro") Integer intIdCentro
    );

    @GET("ListarAccesosImpresoraXUsuario")
    Call<ArrayList<AccesosImpresoraXUsuario>> listarAccesosImpresoraXUsuario(
            @Query("strUsuario") String strUsuario
    );

    @GET("ValidarUsuario")
    Call<ArrayList<Usuario>> validarUsuario(
            @Query("usuario") String strUsuario,
            @Query("clave") String strClave
    );
}
