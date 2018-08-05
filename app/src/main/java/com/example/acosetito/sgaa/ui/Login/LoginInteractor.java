package com.example.acosetito.sgaa.ui.Login;
import com.example.acosetito.sgaa.data.Model.Usuario;

public interface LoginInteractor {
    interface OnValidateUserListener
    {
        void OnSuccessValidateUser(Usuario user);
        void OnFailureValidateUser(String result);
        void OnSuccessValidateInputFields(String result);
        void OnFailureValidateInputFields(String result);
    }

    void validateUser(String user, String password, Integer idTerminal, OnValidateUserListener listener);
    void validateInputFields(String user, String password, OnValidateUserListener listener);
}
