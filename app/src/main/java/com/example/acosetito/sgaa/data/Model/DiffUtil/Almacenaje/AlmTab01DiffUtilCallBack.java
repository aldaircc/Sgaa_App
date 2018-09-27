package com.example.acosetito.sgaa.data.Model.DiffUtil.Almacenaje;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionTransito;

import java.util.ArrayList;

public class AlmTab01DiffUtilCallBack extends DiffUtil.Callback{

    ArrayList<UbicacionTransito> newList;
    ArrayList<UbicacionTransito> oldList;

    public AlmTab01DiffUtilCallBack(ArrayList<UbicacionTransito> newList,
                                     ArrayList<UbicacionTransito> oldList) {
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
        boolean result = newList.get(newItemPosition).getId_Ubicacion().equals(oldList.get(oldItemPosition).getId_Ubicacion());
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
        UbicacionTransito newModel = newList.get(newItemPosition);
        UbicacionTransito oldModel = oldList.get(oldItemPosition);
        Bundle diff = new Bundle();

        if (newModel.getAlmacen().equals(oldModel.getAlmacen())){
            diff.putString("Almacen", newModel.getAlmacen());
        }

        if (!newModel.getSector().equals(oldModel.getSector())){
            diff.putString("Sector", newModel.getSector());
        }

        if (!newModel.getPasillo().equals(oldModel.getPasillo())){
            diff.putString("Pasillo", newModel.getPasillo());
        }

        if (!newModel.getUbicacion().equals(oldModel.getUbicacion())){
            diff.putString("Ubicacion", newModel.getUbicacion());
        }

        if (!newModel.getTotal().equals(oldModel.getTotal())){
            diff.putString("Total", String.valueOf(newModel.getTotal()));
        }

        if (diff.size() == 0){
            return  null;
        }

        return diff;
    }
}
