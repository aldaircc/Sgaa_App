package com.example.acosetito.sgaa.ui.Recibo.Tab_03;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.ApiClient;
import com.example.acosetito.sgaa.data.WebService.ReciboClient;
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
}
