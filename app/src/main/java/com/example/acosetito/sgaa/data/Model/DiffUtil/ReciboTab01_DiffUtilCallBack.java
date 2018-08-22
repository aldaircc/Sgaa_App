package com.example.acosetito.sgaa.data.Model.DiffUtil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.acosetito.sgaa.data.Model.Recepcion.ListarRecepcionesXUsuario;

import java.util.ArrayList;

public class ReciboTab01_DiffUtilCallBack extends DiffUtil.Callback{

    ArrayList<ListarRecepcionesXUsuario> newList;
    ArrayList<ListarRecepcionesXUsuario> oldList;

    public ReciboTab01_DiffUtilCallBack(ArrayList<ListarRecepcionesXUsuario> newList, ArrayList<ListarRecepcionesXUsuario> oldList){
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
         boolean result = newList.get(newItemPosition).getId_Tx().equals(oldList.get(oldItemPosition).getId_Tx());
         return result;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
         int result = newList.get(newItemPosition).compareTo(oldList.get(oldItemPosition));
         return result == 0;
    }

     @Nullable
     @Override
     public Object getChangePayload(int oldItemPosition, int newItemPosition) {
         ListarRecepcionesXUsuario newModel = newList.get(newItemPosition);
         ListarRecepcionesXUsuario oldModel = oldList.get(oldItemPosition);

         Bundle diff = new Bundle();

         if (newModel.getId_Tx().equals(oldModel.getId_Tx())){
            diff.putString("Id_Tx", newModel.getId_Tx());
         }

         if (!newModel.getNumOrden().equals(oldModel.getNumOrden())){
             diff.putString("NumOrden", newModel.getNumOrden());
         }

         if (!newModel.getCliente().equals(oldModel.getCliente())){
             diff.putString("Cuenta", newModel.getCliente());
         }

         if (!newModel.getProveedor().equals(oldModel.getProveedor())){
             diff.putString("Proveedor", newModel.getProveedor());
         }

         if (!newModel.getFlagPausa().equals(oldModel.getFlagPausa())){
             diff.putBoolean("FlagPausa", newModel.getFlagPausa());
         }

         if (diff.size() == 0){
            return null;
         }
        return  diff;
     }
}
