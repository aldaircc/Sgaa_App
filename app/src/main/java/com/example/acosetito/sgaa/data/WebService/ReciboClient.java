package com.example.acosetito.sgaa.data.WebService;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.Causal;
import com.example.acosetito.sgaa.data.Model.Recepcion.ImpUA;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarRecepcionesXUsuario;
import com.example.acosetito.sgaa.data.Model.Recepcion.TxUbicacion;
import com.example.acosetito.sgaa.data.Model.Recepcion.UA;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReciboClient {

    @GET("ListarRecepcionesXUsuario_V2")
    Call<ArrayList<ListarRecepcionesXUsuario>> getRecepcionesXUsuario(
            @Query("strUsuario") String strUsuario,
            @Query("intIdAlmacen") Integer intIdAlmacen,
            @Query("intIdMuelle") Integer intIdMuelle
    );

    @GET("TxDetalleXTx_v2")
    Call<List<ListarDetalleTx>> getDetailTx(
            @Query("strIdTx") String strIdTx
    );

    @Headers({"Accept: application/json", "Content-Type:application/json"})
    @POST("CerrarRecepcion/idTx/idEstado/usuario")
    Call<Mensaje> getCerrarRecepcion(
            @Body JsonObject objParams
    );

    @GET("ListarUAXProductoTx")
    Call<List<UAXProductoTxA>> getUAXProductoTx(
        @Query("strIdTx") String strIdTx,
        @Query("intIdProducto") Integer intIdProducto,
        @Query("intItem") Integer intItem
    );

    @GET("ValidarReciboTransferenciaSerie")
    Call<Mensaje> validarReciboTransferenciaSerie(
        @Query("strNumOrden") String strNumOrden,
        @Query("strSerie") String strSerie,
        @Query("intItem") Integer intItem
    );

    @GET("ValidarReciboSerie")
    Call<Mensaje> validarReciboSerie(
      @Query("strSerie") String strSerie,
      @Query("strIdTx") String strIdTx,
      @Query("intIdProducto") Integer intIdProducto
    );

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("ValidarUAReciboTransferencia/ua")
    Call<Mensaje> validarUAReciboTransferencia(
            @Body UA ua
    );

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("ValidarUARecibo/ua")
    Call<Mensaje> validarUARecibo(
            @Body HashMap<String, Object> ua
    );

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("RegistrarUATransito/TxUbi")
    Call<String> registerUATransito(
            @Body HashMap<String, TxUbicacion> txUbi
    );

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("RecepcionUA/ua")
    Call<Mensaje> registrarUA(
            @Body HashMap<String, Object> ua
    );

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("RecepcionUATransferencia/ua")
    Call<Mensaje> registrarUATransferencia(
      @Body HashMap<String, Object> ua
    );

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("ValidarPallet/strPallet/IdAlmacen")
    Call<Mensaje> validarPallet(
            @Body JsonObject obj
    );

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("InsertarPallet/idAlmacen/user/idCentro")
    Call<String> insertarPallet(
            @Body JsonObject obj
    );

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("RegistrarPallet/ua")
    Call<Mensaje> registrarPallet(
            @Body HashMap<String, ArrayList<ImpUA>> ua
    );

    @GET("ListarCausalesXModulo")
    Call<ArrayList<Causal>> listarCausalesXModulo(
            @Query("intIdCliente") Integer intIdCliente,
            @Query("intIdModulo") Integer intIdModulo
    );
}
