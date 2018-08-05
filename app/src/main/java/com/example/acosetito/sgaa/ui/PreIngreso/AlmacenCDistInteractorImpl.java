package com.example.acosetito.sgaa.ui.PreIngreso;
import com.example.acosetito.sgaa.data.Model.Almacen;
import com.example.acosetito.sgaa.data.Model.CentroDistribucion;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.ApiClient;
import com.example.acosetito.sgaa.data.WebService.UserClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;

public class AlmacenCDistInteractorImpl implements AlmacenCDistInteractor {
    @Override
    public void getCenterByUser(String user, final OnAlmacenCDistListener listener) {

        UserClient userClient = (ApiClient.getApiClient(Global.usuarioService)).create(UserClient.class);
        Call<List<CentroDistribucion>> call = userClient.getCenterByUser(user);
        call.enqueue(new Callback<List<CentroDistribucion>>() {
            @Override
            public void onResponse(Call<List<CentroDistribucion>> call, Response<List<CentroDistribucion>> response) {
                switch (response.code()){
                    case 200:
                        List<CentroDistribucion> lstCD = response.body();
                        listener.OnSuccessGetCenterByUser(lstCD);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<CentroDistribucion>> call, Throwable t) {
                listener.OnFailureGetCenterByUser(t.getMessage());
            }
        });
    }

    @Override
    public void getWarehouseByUser(String user, Integer intIdCentro, final OnAlmacenCDistListener listener) {
        try {
            UserClient userClient = (ApiClient.getApiClient(Global.usuarioService)).create(UserClient.class);
            Call<List<Almacen>> call = userClient.getWarehouseByUser(user, intIdCentro);
            call.enqueue(new Callback<List<Almacen>>() {
                @Override
                public void onResponse(Call<List<Almacen>> call, Response<List<Almacen>> response) {
                    switch (response.code()){
                        case 200:
                            List<Almacen> lstWarehouse = response.body();
                            listener.OnSuccessGetWarehouseByUser(lstWarehouse);
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void onFailure(Call<List<Almacen>> call, Throwable t) {
                    listener.OnFailureGetWarehouseByUser(t.getMessage());
                }
            });
        }catch (Exception ex){
            Log.e("getWarehouseByUser", ex.getMessage());
        }
    }

    @Override
    public void validateInputFields(Integer intIdCenter, Integer intIdWarehouse, final OnAlmacenCDistListener listener) {

        String result = "";

        if (intIdCenter == 0){
            result += "\n-Seleccione centro.";
        }
        if (intIdWarehouse == 0){
            result += "\n-Seleccione almacén.";
        }
        if (result.length() != 0){
            listener.OnFailureValidateInputFields(result);
        }else{
            listener.OnSuccessValidateInputFields(result);
        }
    }
}