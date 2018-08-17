package com.example.acosetito.sgaa.data.Model.DiffUtil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.acosetito.sgaa.data.Model.Impresora.AccesosImpresoraXUsuario;

import java.util.ArrayList;

public class ImpresoraDiffUtilCallBack extends DiffUtil.Callback{

    ArrayList<AccesosImpresoraXUsuario> newList;
    ArrayList<AccesosImpresoraXUsuario> oldList;

    public ImpresoraDiffUtilCallBack(ArrayList<AccesosImpresoraXUsuario> newList,
                                     ArrayList<AccesosImpresoraXUsuario> oldList) {
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
        boolean result = newList.get(newItemPosition).getId_Impresora().equals(oldList.get(oldItemPosition).getId_Impresora());
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
        AccesosImpresoraXUsuario newModel = newList.get(newItemPosition);
        AccesosImpresoraXUsuario oldModel = oldList.get(oldItemPosition);
        Bundle diff = new Bundle();

        if (newModel.getIpImpresora() != (oldModel.getIpImpresora()) ){
            diff.putString("Ip", newModel.getIpImpresora());
        }

        if (!newModel.getNombre().equals(oldModel.getNombre())){
            diff.putString("Nombre", newModel.getNombre());
        }

        if (!newModel.getPuertoImpresora().equals(oldModel.getPuertoImpresora())){
            diff.putString("Puerto", newModel.getPuertoImpresora());
        }

        if (diff.size() == 0){
            return  null;
        }

        return diff;
    }
}