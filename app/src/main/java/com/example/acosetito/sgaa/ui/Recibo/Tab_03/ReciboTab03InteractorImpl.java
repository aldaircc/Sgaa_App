package com.example.acosetito.sgaa.ui.Recibo.Tab_03;
import android.util.Log;

import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.TxUbicacion;
import com.example.acosetito.sgaa.data.Model.Recepcion.UA;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.ApiClient;
import com.example.acosetito.sgaa.data.WebService.ReciboClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReciboTab03InteractorImpl implements ReciboTab03Interactor {
    @Override
    public void getUAsProductoTx(String strIdTx, Integer intIdProducto, Integer intItem, final OnListenerReciboTab03 listener) {

        ReciboClient reciboClient = (ApiClient.getApiClient(Global.recepcionService)).create(ReciboClient.class);
        Call<List<UAXProductoTxA>> call = reciboClient.getUAXProductoTx(strIdTx, intIdProducto, intItem);
        call.enqueue(new Callback<List<UAXProductoTxA>>() {
            @Override
            public void onResponse(Call<List<UAXProductoTxA>> call, Response<List<UAXProductoTxA>> response) {
                switch (response.code()){
                    case 200:
                        List<UAXProductoTxA> list;
                        list = response.body();
                        listener.OnSuccessGetUAsProductoTx(list);
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
    public void validateReciboTransferSerie(String strNumOrden, String strSerie, Integer intItem, final OnListenerReciboTab03 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.recepcionService)).create(ReciboClient.class);
        Call<Mensaje> call = reciboClient.validarReciboTransferenciaSerie(strNumOrden, strSerie, intItem);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                switch (response.code()){
                    case 200:
                        Mensaje message = response.body();
                        listener.OnSuccessValidateReciboTransferSerie(message);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                listener.OnFailureValidateReciboTransferSerie(t.getMessage());
            }
        });
    }

    @Override
    public void validateReciboSerie(String strSerie, String strIdTx, Integer intIdProducto, final OnListenerReciboTab03 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.recepcionService)).create(ReciboClient.class);
        Call<Mensaje> call = reciboClient.validarReciboSerie(strSerie, strIdTx, intIdProducto);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                switch (response.code()){
                    case 200:
                        Mensaje message = response.body();
                        listener.OnSucessValidateReciboSerie(message);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                listener.OnFailureValidateReciboSerie(t.getMessage());
            }
        });
    }

    @Override
    public void validateEmptyBarCode(String barCode, OnListenerReciboTab03 listener) {
        String message = "";

        if (barCode.trim().isEmpty()){
            message += "Ingrese un codigo de barras.";
        }

        if (message.isEmpty()){
            listener.OnSuccessValidateEmptyBarCode(message);
        }else{
            listener.OnFailureValidateEmptyBarCode(message);
        }
    }

    @Override
    public void validateUAReciboTransferencia(UA ua, final OnListenerReciboTab03 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.recepcionService)).create(ReciboClient.class);
        //JsonObject objParam = new JsonObject();
        //objParam.add("ua", ua);
        Call<Mensaje> call = reciboClient.validarUAReciboTransferencia(ua);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                switch (response.code()){
                    case 200:
                        Mensaje message = response.body();
                        listener.OnSuccessValidateUAReciboTransferencia(message);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                listener.OnFailureValidateUAReciboTransferencia(t.getMessage());
            }
        });
    }

    @Override
    public void validateUARecibo(UA ua, final OnListenerReciboTab03 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.recepcionService)).create(ReciboClient.class);

        HashMap<String, Object> objParam = new HashMap<>();
        objParam.put("ua", ua);

        Call<Mensaje> call = reciboClient.validarUARecibo(objParam);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                switch (response.code()){
                    case 200:
                        Mensaje message = response.body();
                        listener.OnSuccessValidateUARecibo(message);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                listener.OnFailureValidateUARecibo(t.getMessage());
            }
        });
    }

    @Override
    public void registerUATransito(TxUbicacion txUbi, final OnListenerReciboTab03 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.recepcionService)).create(ReciboClient.class);
        HashMap<String, TxUbicacion> objParam = new HashMap<>();
        objParam.put("TxUbi", txUbi);
        Call<String> call = reciboClient.registerUATransito(objParam);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                switch (response.code()){
                    case 200:
                        String result = response.body();
                        listener.OnSuccessRegisterUATransito(result);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.OnFailureRegisterUATransito(t.getMessage());
            }
        });
    }

    @Override
    public void registerUA(UA ua, final OnListenerReciboTab03 listener) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .create();
        JsonObject objJson = new JsonObject();
        Object nullable = null;
        objJson.addProperty("Accion" , ua.getAccion());
        objJson.addProperty("BarraUbicacion", ua.getBarraUbicacion()); //optional
        objJson.addProperty("Cantidad",  ua.getCantidad());
        objJson.addProperty("CantidadAveriada", ua.getCantidadAveriada());
        objJson.addProperty("Codigo", ua.getCodigo());
        //objJson.addProperty("FecRegIdTerminalRF_Pallet", ua.getFecRegIdTerminalRF_Pallet()); //optional
        //objJson.addProperty("FechaAnulacion", ua.getFechaAnulacion()); //optional
        objJson.addProperty("FechaEmision","/Date("+ gson.toJson(ua.getFechaEmision().getTime()) + ")/");
        //objJson.addProperty("FechaIngreso", ua.getFechaIngreso()); //optional
        //objJson.addProperty("FechaModificacion", ua.getFechaModificacion()); //optional
        //objJson.addProperty("FechaRegistro", ua.getFechaRegistro()); //optional
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
        //objParam.put("ua", ua);
        objParam.put("ua", objJson);
        Call<Mensaje> call = reciboClient.registerUA(objParam);
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
    public void registerUATransferencia(UA ua, final OnListenerReciboTab03 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.recepcionService)).create(ReciboClient.class);
        HashMap<String, Object> objParam = new HashMap<>();
        objParam.put("ua", ua);
        Call<Mensaje> call = reciboClient.registerUATransferencia(objParam);
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
                listener.OnFailureRegisterUASTransferencia(t.getMessage());
            }
        });
    }
}
