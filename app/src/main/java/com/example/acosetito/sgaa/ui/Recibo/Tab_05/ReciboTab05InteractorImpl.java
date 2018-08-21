package com.example.acosetito.sgaa.ui.Recibo.Tab_05;
import com.example.acosetito.sgaa.data.Model.Impresora.ListaEtiqueta;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ImpUA;
import com.example.acosetito.sgaa.data.Model.Recepcion.TxUbicacion;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.ApiClient;
import com.example.acosetito.sgaa.data.WebService.ImpresionClient;
import com.example.acosetito.sgaa.data.WebService.ReciboClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReciboTab05InteractorImpl implements ReciboTab05Interactor{
    @Override
    public void validatePallet(String strPallet, int intIdAlmacen, final OnListenerReciboTab05 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.baseUrl, Global.recepcionService)).create(ReciboClient.class);
        JsonObject obj = new JsonObject();
        obj.addProperty("strPallet", strPallet);
        obj.addProperty("IdAlmacen", intIdAlmacen);
        Call<Mensaje> call = reciboClient.validarPallet(obj);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                switch (response.code()){
                    case 200:
                        Mensaje message = response.body();
                        listener.OnSuccessValidatePallet(message);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                listener.OnFailureValidatePallet(t.getMessage());
            }
        });
    }

    @Override
    public void insertPallet(Integer intId_Almacen, String strUsuario, Integer intId_Centro, final OnListenerReciboTab05 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.baseUrl, Global.recepcionService)).create(ReciboClient.class);
        JsonObject obj = new JsonObject();
        obj.addProperty("idAlmacen", intId_Almacen);
        obj.addProperty("user", strUsuario);
        obj.addProperty("idCentro", intId_Centro);
        Call<String> call = reciboClient.insertarPallet(obj);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                switch (response.code())
                {
                    case 200:
                        listener.OnSuccessInsertPallet(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.OnFailureInsertPallet(t.getMessage());
            }
        });
    }

    @Override
    public void printListaEtq(ArrayList<ListaEtiqueta> lista, String strFormato, String strNombreImpresora, Boolean bolEsScript, final OnListenerReciboTab05 listener) {
        ImpresionClient impresionClient = (ApiClient.getApiClient(Global.baseUrlPrint, Global.impresoraService)).create(ImpresionClient.class);
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("lista", lista);
        obj.put("formato", strFormato);
        obj.put("nombreImpresora", strNombreImpresora);
        obj.put("esScript", bolEsScript);

        Call<Mensaje> call = impresionClient.imprimirListaEtiquetas(obj);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessPrintListaEtq(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                listener.OnFailurePrintListaEtq(t.getMessage());
            }
        });
    }

    @Override
    public void registerPallet(ArrayList<ImpUA> ua, final OnListenerReciboTab05 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.baseUrl, Global.recepcionService).create(ReciboClient.class));
        HashMap<String, ArrayList<ImpUA>> param = new HashMap<>();
        param.put("ua", ua);
        Call<Mensaje> call = reciboClient.registrarPallet(param);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessRegisterPallet(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                listener.OnFailureRegisterPallet(t.getMessage());
            }
        });
    }

    @Override
    public void getUAsProductoTx(String strIdTx, Integer intIdProducto, Integer intItem, final OnListenerReciboTab05 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.baseUrl, Global.recepcionService)).create(ReciboClient.class);
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
    public void registerUATransito(TxUbicacion txUbi, final OnListenerReciboTab05 listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.baseUrl, Global.recepcionService).create(ReciboClient.class));
        HashMap<String, TxUbicacion> param = new HashMap<>();
        param.put("TxUbi", txUbi);
        Call<String> call = reciboClient.registerUATransito(param);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessRegisterUATransito(response.body());
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
}
