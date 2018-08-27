package com.example.acosetito.sgaa.ui.Etiqueta;

import com.example.acosetito.sgaa.data.Model.Recepcion.SubAlmacenXCuenta;
import com.example.acosetito.sgaa.data.Model.Recepcion.UMxProducto;

import java.util.ArrayList;

public interface EtqInteractor {
    interface OnListenerEtq{
        void OnSuccessListUMxProducto(ArrayList<UMxProducto> list);
        void OnFailureListUMxProducto(String result);
        void OnSuccessListSubAlmacenesXCuenta(ArrayList<SubAlmacenXCuenta> list);
        void OnFailureListSubAlmacenesXCuenta(String result);
    }
    void listUMxProducto(Integer intIdProducto, OnListenerEtq listener);
    void listSubAlmacenesXCuenta(Integer intIdCuenta, Integer intIdAlmacen, OnListenerEtq listener);
}
