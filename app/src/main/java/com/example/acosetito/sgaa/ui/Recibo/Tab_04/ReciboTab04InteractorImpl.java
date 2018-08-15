package com.example.acosetito.sgaa.ui.Recibo.Tab_04;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.UA;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.ApiClient;
import com.example.acosetito.sgaa.data.WebService.ReciboClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReciboTab04InteractorImpl implements ReciboTab04Interactor{

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();

    @Override
    public void getUAsProductoTx(String strIdTx, Integer intIdProducto, Integer intItem, final OnListenerReciboTab04 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.recepcionService)).create(ReciboClient.class);
        Call<List<UAXProductoTxA>> call = reciboClient.getUAXProductoTx(strIdTx, intIdProducto, intItem);
        call.enqueue(new Callback<List<UAXProductoTxA>>() {
            @Override
            public void onResponse(Call<List<UAXProductoTxA>> call, Response<List<UAXProductoTxA>> response) {
                switch (response.code()){
                    case 200:
                        List<UAXProductoTxA> list;
                        list = response.body();
                        listener.OnSuccessGetUAsProductoTx((ArrayList<UAXProductoTxA>) list);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<UAXProductoTxA>> call, Throwable t) {
                listener.OnFailureGetUAsProductoTx(t.getMessage());
            }
        });
    }

    @Override
    public void registerUA(UA ua, final OnListenerReciboTab04 listener) {
        JsonObject objJson = new JsonObject();
        Object nullable = null;
        objJson.addProperty("Accion" , ua.getAccion());
        objJson.addProperty("BarraUbicacion", ua.getBarraUbicacion()); //optional
        objJson.addProperty("Cantidad",  ua.getCantidad());
        objJson.addProperty("CantidadAveriada", ua.getCantidadAveriada());
        objJson.addProperty("Codigo", ua.getCodigo());
        objJson.addProperty("FechaEmision","/Date("+ gson.toJson(ua.getFechaEmision().getTime()) + ")/");
        objJson.addProperty("FechaVencimiento", "/Date("+gson.toJson(ua.getFechaVencimiento().getTime())+ ")/");
        objJson.addProperty("FlagActivo", ua.getFlagActivo()); //optional
        objJson.addProperty("FlagAnulado",ua.getFlagAnulado());
        objJson.addProperty("FlagAveriado", ua.getFlagAveriado()); //optional
        objJson.addProperty("FlagDisponible", ua.getFlagDisponible()); //optional
        objJson.addProperty("Id_Almacen",ua.getId_Almacen());
        objJson.addProperty("Id_Estado", ua.getId_Estado()); //optional
        objJson.addProperty("Id_Marca", ua.getId_Marca()); //optional
        objJson.addProperty("Id_Producto",ua.getId_Producto());
        objJson.addProperty("Id_TerminalRF",ua.getId_TerminalRF());
        objJson.addProperty("Id_TerminalRF_Pallet", ua.getId_TerminalRF()); //optional
        objJson.addProperty("Id_Tx",ua.getId_Tx());
        objJson.addProperty("Id_Tx_Anterior", ua.getId_Tx_Anterior()); //optional
        objJson.addProperty("Id_Tx_Ubi",ua.getId_Tx_Ubi());
        objJson.addProperty("Id_UM", ua.getId_UM()); //optional
        objJson.addProperty("Id_Ubicacion",ua.getId_Ubicacion());
        objJson.addProperty("Id_UbicacionOld", ua.getId_UbicacionOld()); //optional
        objJson.addProperty("Item",ua.getItem());
        objJson.addProperty("LoteLab",ua.getLoteLab());
        objJson.addProperty("LotePT", ua.getNota()); //optional
        objJson.addProperty("Nota", ua.getNota()); //optional
        objJson.addProperty("Observacion",ua.getObservacion());
        objJson.addProperty("PalletCodBarra", ua.getPalletCodBarra()); //optional
        objJson.addProperty("Producto", ua.getProducto()); //optional
        objJson.addProperty("Saldo",ua.getSaldo());
        objJson.addProperty("Serie", ua.getSerie()); //optional
        objJson.addProperty("UA_CodBarra",ua.getUA_CodBarra());
        objJson.addProperty("UsuarioAnulacion", ua.getUsuarioAnulacion());
        objJson.addProperty("UsuarioModificacion", ua.getUsuarioModificacion() );
        objJson.addProperty("UsuarioRegistro",ua.getUsuarioRegistro());

        ReciboClient reciboClient = (ApiClient.getApiClient(Global.recepcionService)).create(ReciboClient.class);
        HashMap<String, Object> objParam = new HashMap<>();
        objParam.put("ua", objJson);
        Call<Mensaje> call = reciboClient.registrarUA(objParam);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                switch (response.code()){
                    case 200:
                        Mensaje message = response.body();
                        listener.OnSuccessRegisterUA(message);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                listener.OnFailureRegisterUA(t.getMessage());
            }
        });
    }

    @Override
    public void registerUATransferencia(UA ua, final OnListenerReciboTab04 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.recepcionService)).create(ReciboClient.class);
        HashMap<String, Object> objParam = new HashMap<>();
        objParam.put("ua", ua);
        Call<Mensaje> call = reciboClient.registrarUATransferencia(objParam);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                switch (response.code()){
                    case 200:
                        Mensaje message = response.body();
                        listener.OnSuccessRegisterUATransferencia(message);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                listener.OnFailureRegisterUATransferencia(t.getMessage());
            }
        });
    }
}
