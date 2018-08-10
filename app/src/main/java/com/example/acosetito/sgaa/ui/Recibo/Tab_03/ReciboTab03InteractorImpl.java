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
        String json = gson.toJson(ua);

        JsonObject objJson = new JsonObject();
        objJson.addProperty("FechaEmision", gson.toJson(ua.getFechaEmision()));
        objJson.addProperty("FechaVencimiento", gson.toJson(ua.getFechaEmision()));

        //objJson.addProperty("Accion" ,null);
        //objJson.addProperty("BarraUbicacion",null);
        objJson.addProperty("Cantidad", 100.0);
        objJson.addProperty("CantidadAveriada", 0.0);
        //objJson.addProperty("Codigo", null);
        //objJson.addProperty("FecRegIdTerminalRF_Pallet":null);
        //objJson.addProperty("FechaAnulacion":null);
        objJson.addProperty("FechaEmision",gson.toJson(ua.getFechaEmision()));
        //objJson.addProperty("FechaIngreso":null);
        //objJson.addProperty("FechaModificacion":null);
        //objJson.addProperty("FechaRegistro":null);
        objJson.addProperty("FechaVencimiento",gson.toJson(ua.getFechaVencimiento()));
        //objJson.addProperty("FlagActivo",null);
        objJson.addProperty("FlagAnulado",false);
        //objJson.addProperty("FlagAveriado":null);
        //objJson.addProperty("FlagDisponible":null);
        objJson.addProperty("Id_Almacen",1);
        //objJson.addProperty("Id_Estado":null);
        //objJson.addProperty("Id_Marca":null);
        objJson.addProperty("Id_Producto",2577);
        objJson.addProperty("Id_TerminalRF",1);
        //objJson.addProperty("Id_TerminalRF_Pallet",null);
        objJson.addProperty("Id_Tx","A20182040001");
        //objJson.addProperty("Id_Tx_Anterior",null);
        objJson.addProperty("Id_Tx_Ubi","201822100001");
        //objJson.addProperty("Id_UM",null);
        objJson.addProperty("Id_Ubicacion",0);
        //objJson.addProperty("Id_UbicacionOld":null);
        objJson.addProperty("Item",1);
        objJson.addProperty("LoteLab",8787);
       //objJson.addProperty("LotePT",null);
        //objJson.addProperty("Nota",null);
        objJson.addProperty("Observacion","");
        //objJson.addProperty("PalletCodBarra",null);
        //objJson.addProperty("Producto",null);
        objJson.addProperty("Saldo",100.00);
        //objJson.addProperty("Serie",null);
        objJson.addProperty("UA_CodBarra","A20182200001");
        //objJson.addProperty("UsuarioAnulacion",null);
        //objJson.addProperty("UsuarioModificacion",null);
        objJson.addProperty("UsuarioRegistro","ADMIN");

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
