package com.example.acosetito.sgaa.ui.PreIngreso;
import com.example.acosetito.sgaa.data.Model.Almacen;
import com.example.acosetito.sgaa.data.Model.CentroDistribucion;
import java.util.List;

public class AlmacenCDistPresenterImpl implements AlmacenCDistPresenter, AlmacenCDistInteractor.OnAlmacenCDistListener{

    private AlmacenCDistView almacenCDistView;
    private AlmacenCDistInteractor almacenCDistInteractor;

    public AlmacenCDistPresenterImpl(AlmacenCDistView view)
    {
        this.almacenCDistView = view;
        this.almacenCDistInteractor = new AlmacenCDistInteractorImpl();
    }

    @Override
    public void OnSuccessGetCenterByUser(List<CentroDistribucion> lstCD) {
        almacenCDistView.sourceCentroDistribucion(lstCD);
    }

    @Override
    public void OnFailureGetCenterByUser(String result) {
        almacenCDistView.showFailureGetCentrosDistribucion(result);
    }

    @Override
    public void OnSuccessGetWarehouseByUser(List<Almacen> lstWarehouse) {
        almacenCDistView.sourceWarehouse(lstWarehouse);
    }

    @Override
    public void OnFailureGetWarehouseByUser(String result) {
        almacenCDistView.showFailureGetWarehouse(result);
    }

    @Override
    public void getCenterByUser(String user) {
        almacenCDistInteractor.getCenterByUser(user, this);
    }

    @Override
    public void getWarehouseByUser(String user, Integer intIdCentro) {
        almacenCDistInteractor.getWarehouseByUser(user, intIdCentro, this);
    }

    @Override
    public void navigateToMenu() {
        if (almacenCDistView != null)
            almacenCDistView.navigateToMenu();
    }

    @Override
    public void validateInputFields(Integer intIdCenter, Integer intIdWarehouse) {
        almacenCDistInteractor.validateInputFields(intIdCenter, intIdWarehouse, this);
    }


    @Override
    public void OnSuccessValidateInputFields(String result) {
        almacenCDistView.showMessageValidation(result);
    }

    @Override
    public void OnFailureValidateInputFields(String result) {
        almacenCDistView.showMessageValidation(result);
    }
}
