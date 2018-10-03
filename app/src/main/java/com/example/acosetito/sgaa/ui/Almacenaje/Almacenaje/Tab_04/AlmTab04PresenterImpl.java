package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_04;
import com.example.acosetito.sgaa.data.Model.Almacenaje.SectorXAlmacen;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionDisponible;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionXCodigoBarra;

import java.util.ArrayList;

public class AlmTab04PresenterImpl implements AlmTab04Presenter, AlmTab04Interactor.OnListenerAlmTab04{

    private AlmTab04View view;
    private AlmTab04Interactor interactor;

    public AlmTab04PresenterImpl(AlmTab04View view) {
        this.view = view;
        this.interactor = new AlmTab04InteractorImpl();
    }

    @Override
    public void OnSuccessListarSectoresXAlmacen(ArrayList<SectorXAlmacen> list) {
        view.resultListarSector(list);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureListarSectoresXAlmacen(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void OnSuccessListarMasUbicacionesDisponibles(ArrayList<UbicacionDisponible> list) {
        view.resultListarMasUbicacionesDisponibles(list);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureListarMasUbicacionesDisponibles(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void OnSuccessListarUbicacionXCodigoBarra(ArrayList<UbicacionXCodigoBarra> list) {
        view.resultListarUbicacionXCodigoBarra(list);
        view.hideProgressDialog();
    }

    @Override
    public void OnFailureListarUbicacionXCodigoBarra(String result) {
        view.showFailureRequest(result);
        view.hideProgressDialog();
    }

    @Override
    public void listarSectoresXAlmacen(Integer intIdAlmacen) {
        view.showProgressDialog();
        interactor.listarSectoresXAlmacen(intIdAlmacen, this);
    }

    @Override
    public void listarMasUbicacionDisponibles(Integer intIdAlmacen, Integer intIdMarca, Integer intIdCondicion, Integer intIdSector) {
        view.showProgressDialog();
        interactor.listarMasUbicacionesDisponibles(intIdAlmacen, intIdMarca, intIdCondicion, intIdSector, this);
    }

    @Override
    public void listarUbicacionXCodigoBarra(String strUbi, Integer intIdAlmacen) {
        view.showProgressDialog();
        interactor.listarUbicacionXCodigoBarra(strUbi, intIdAlmacen, this);
    }

    @Override
    public void navigateToTab03(Integer intId_Condicion, String strCod_Prod, String strProducto, String strCod_Barra, String strCod_UAPallet, String strSector, String strPasillo, String strFila, Integer intId_Ubicacion, Integer intColumna, Integer intNivel, Integer intPosicion, Integer intCountPallets, Integer total) {
        view.navigateToTab03(intId_Condicion,strCod_Prod, strProducto, strCod_Barra, strCod_UAPallet, strSector, strPasillo, strFila, intId_Ubicacion, intColumna, intNivel, intPosicion, intCountPallets, total);
    }

}
