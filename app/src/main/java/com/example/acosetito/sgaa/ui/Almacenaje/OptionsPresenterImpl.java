package com.example.acosetito.sgaa.ui.Almacenaje;

import android.app.Activity;

public class OptionsPresenterImpl implements OptionsPresenter{

    OptionsView view;
    public OptionsPresenterImpl(OptionsView view) {
        this.view = view;
    }

    @Override
    public void navigateToTab01() {
        view.showProgressDialog();
        view.navigateToTab01();
        view.hideProgressDialog();
    }
}
