package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_04;

import com.example.acosetito.sgaa.data.Model.Almacenaje.SectorXAlmacen;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionDisponible;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionXCodigoBarra;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.AlmacenajeClient;
import com.example.acosetito.sgaa.data.WebService.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlmTab04InteractorImpl implements AlmTab04Interactor{
    @Override
    public void listarSectoresXAlmacen(Integer intIdAlmacen, final OnListenerAlmTab04 listener) {
        AlmacenajeClient almClient = (ApiClient.getApiClient(Global.baseUrl, Global.almacenajeService).create(AlmacenajeClient.class));
        Call<ArrayList<SectorXAlmacen>> call = almClient.listarSectoresXAlmacen(intIdAlmacen);
        call.enqueue(new Callback<ArrayList<SectorXAlmacen>>() {
            @Override
            public void onResponse(Call<ArrayList<SectorXAlmacen>> call, Response<ArrayList<SectorXAlmacen>> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessListarSectoresXAlmacen(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SectorXAlmacen>> call, Throwable t) {
                listener.OnFailureListarSectoresXAlmacen(t.getMessage());
            }
        });
    }

    @Override
    public void listarMasUbicacionesDisponibles(Integer intIdAlmacen, Integer intIdMarca, Integer intIdCondicion, Integer intIdSector, final OnListenerAlmTab04 listener) {
        AlmacenajeClient almClient = (ApiClient.getApiClient(Global.baseUrl, Global.almacenajeService).create(AlmacenajeClient.class));
        Call<ArrayList<UbicacionDisponible>> call = almClient.listarMasUbicacionesDisponibles(intIdAlmacen, intIdMarca, intIdCondicion, intIdSector);
        call.enqueue(new Callback<ArrayList<UbicacionDisponible>>() {
            @Override
            public void onResponse(Call<ArrayList<UbicacionDisponible>> call, Response<ArrayList<UbicacionDisponible>> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessListarMasUbicacionesDisponibles(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UbicacionDisponible>> call, Throwable t) {
                listener.OnFailureListarMasUbicacionesDisponibles(t.getMessage());
            }
        });
    }

    @Override
    public void listarUbicacionXCodigoBarra(String strUbi, Integer intIdAlmacen, final OnListenerAlmTab04 listener) {
        AlmacenajeClient almClient = (ApiClient.getApiClient(Global.baseUrl, Global.almacenajeService).create(AlmacenajeClient.class));
        Call<ArrayList<UbicacionXCodigoBarra>> call = almClient.listarUbicacionXCodigoBarra(strUbi, intIdAlmacen);
        call.enqueue(new Callback<ArrayList<UbicacionXCodigoBarra>>() {
            @Override
            public void onResponse(Call<ArrayList<UbicacionXCodigoBarra>> call, Response<ArrayList<UbicacionXCodigoBarra>> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessListarUbicacionXCodigoBarra(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UbicacionXCodigoBarra>> call, Throwable t) {
                listener.OnFailureListarUbicacionXCodigoBarra(t.getMessage());
            }
        });
    }
}
