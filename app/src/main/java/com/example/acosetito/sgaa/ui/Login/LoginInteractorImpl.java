package com.example.acosetito.sgaa.ui.Login;
import com.example.acosetito.sgaa.data.Model.Usuario;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.ApiClient;
import com.example.acosetito.sgaa.data.WebService.UserClient;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImpl implements LoginInteractor {
    @Override
    public void validateUser(String user, String password, Integer idTerminal, final OnValidateUserListener listener) {
        UserClient userClient = (ApiClient.getApiClient(Global.baseUrl, Global.usuarioService)).create(UserClient.class);
        Call<ArrayList<Usuario>> call = userClient.validarUsuario(user, password);

        call.enqueue(new Callback<ArrayList<Usuario>>() {
            @Override
            public void onResponse(Call<ArrayList<Usuario>> call, Response<ArrayList<Usuario>> response) {
                switch (response.code())
                {
                    case 200:
                        Usuario objUser = new Usuario();
                        if (response.body().size() != 0){
                            objUser = response.body().get(0);
                        }
                        listener.OnSuccessValidateUser(objUser);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Usuario>> call, Throwable t) {
                listener.OnFailureValidateUser(t.getMessage());
            }
        });
    }

    @Override
    public void validateInputFields(String user, String password, final OnValidateUserListener listener) {

        String result = "";

        if (user.trim().length() == 0){
            result += "\n-Ingrese usuario.";
        }
        if (password.trim().length() == 0){
            result += "\n-Ingrese password.";
        }
        if (result.length() != 0){
            listener.OnSuccessValidateInputFields(result);
        }else{
            listener.OnFailureValidateInputFields(result);
        }
    }

    /**
     @Override
     public void validateUser(String user, String password, Integer idTerminal, final OnValidateUserListener listener) {
     UserClient userClient = (ApiClient.getApiClient(Global.baseUrl, Global.usuarioService)).create(UserClient.class);
     Call<List<Usuario>> call = userClient.validateUserAndroid(user, password, idTerminal);
     call.enqueue(new Callback<List<Usuario>>() {
     @Override
     public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
     switch (response.code())
     {
     case 200:
     Usuario objUser = new Usuario();
     if (response.body().size() != 0){
     objUser = response.body().get(0);
     }
     listener.OnSuccessValidateUser(objUser);
     break;
     default:
     break;
     }
     }

     @Override
     public void onFailure(Call<List<Usuario>> call, Throwable t) {
     listener.OnFailureValidateUser(t.getMessage());
     }
     });
     }
     **/
}
