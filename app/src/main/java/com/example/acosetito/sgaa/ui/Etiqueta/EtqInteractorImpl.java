package com.example.acosetito.sgaa.ui.Etiqueta;

import com.example.acosetito.sgaa.data.Model.Recepcion.SubAlmacenXCuenta;
import com.example.acosetito.sgaa.data.Model.Recepcion.UMxProducto;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.ApiClient;
import com.example.acosetito.sgaa.data.WebService.TablasEstaticasClient;
import com.example.acosetito.sgaa.data.WebService.UnidadMedidaClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EtqInteractorImpl implements EtqInteractor{

    @Override
    public void listUMxProducto(Integer intIdProducto, final OnListenerEtq listener) {
        UnidadMedidaClient umClient = (ApiClient.getApiClient(Global.baseUrl, Global.unidadMedidaService).create(UnidadMedidaClient.class));
        Call<ArrayList<UMxProducto>> call = umClient.listarUMxProducto(intIdProducto);
        call.enqueue(new Callback<ArrayList<UMxProducto>>() {
            @Override
            public void onResponse(Call<ArrayList<UMxProducto>> call, Response<ArrayList<UMxProducto>> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessListUMxProducto(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UMxProducto>> call, Throwable t) {
                listener.OnFailureListUMxProducto(t.getMessage());
            }
        });
    }

    @Override
    public void listSubAlmacenesXCuenta(Integer intIdCuenta, Integer intIdAlmacen, final OnListenerEtq listener) {
        TablasEstaticasClient estaticasClient = (ApiClient.getApiClient(Global.baseUrl, Global.tablasEstaticasService).create(TablasEstaticasClient.class));
        Call<ArrayList<SubAlmacenXCuenta>> call = estaticasClient.listarSubAlmacenesXCuenta(intIdCuenta, intIdAlmacen);
        call.enqueue(new Callback<ArrayList<SubAlmacenXCuenta>>() {
            @Override
            public void onResponse(Call<ArrayList<SubAlmacenXCuenta>> call, Response<ArrayList<SubAlmacenXCuenta>> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessListSubAlmacenesXCuenta(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SubAlmacenXCuenta>> call, Throwable t) {
                listener.OnFailureListSubAlmacenesXCuenta(t.getMessage());
            }
        });
    }

}
