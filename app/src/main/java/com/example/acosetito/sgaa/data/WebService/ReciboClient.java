package com.example.acosetito.sgaa.data.WebService;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarRecepcionesXUsuario;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReciboClient {

    @GET("ListarRecepcionesXUsuario_V2")
    Call<List<ListarRecepcionesXUsuario>> getRecepcionesXUsuario(
            @Query("strUsuario") String strUsuario,
            @Query("intIdAlmacen") Integer intIdAlmacen,
            @Query("intIdMuelle") Integer intIdMuelle
    );

    @GET("TxDetalleXTx_v2")
    Call<List<ListarDetalleTx>> getDetailTx(
            @Query("strIdTx") String strIdTx
    );

}
