package com.example.acosetito.sgaa.ui.Main;

import android.view.View;

public class MainPresenterImpl implements  MainPresenter{

    private MainView mainView;

    public MainPresenterImpl(MainView view)
    {
        this.mainView = view;
    }

    @Override
    public void navigateToOption(View view) {
        if (mainView != null)
            mainView.navigateToOption(view);
    }
}
