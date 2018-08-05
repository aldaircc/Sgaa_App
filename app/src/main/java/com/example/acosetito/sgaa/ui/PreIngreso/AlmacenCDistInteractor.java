package com.example.acosetito.sgaa.ui.PreIngreso;
import com.example.acosetito.sgaa.data.Model.Almacen;
import com.example.acosetito.sgaa.data.Model.CentroDistribucion;
import java.util.List;

public interface AlmacenCDistInteractor {
    interface OnAlmacenCDistListener{
        void OnSuccessGetCenterByUser(List<CentroDistribucion> lstCD);
        void OnFailureGetCenterByUser(String result);
        void OnSuccessGetWarehouseByUser(List<Almacen> lstWarehouse);
        void OnFailureGetWarehouseByUser(String result);
        void OnSuccessValidateInputFields(String result);
        void OnFailureValidateInputFields(String result);
    }
    void getCenterByUser(String user, OnAlmacenCDistListener listener);
    void getWarehouseByUser(String user, Integer intIdCentro, OnAlmacenCDistListener listener);
    void validateInputFields(Integer intIdCenter, Integer intIdWarehouse, OnAlmacenCDistListener listener);
}
