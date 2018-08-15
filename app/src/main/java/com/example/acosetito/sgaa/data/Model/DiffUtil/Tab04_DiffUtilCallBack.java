package com.example.acosetito.sgaa.data.Model.DiffUtil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;

import java.util.ArrayList;

public class Tab04_DiffUtilCallBack extends DiffUtil.Callback{

    ArrayList<UAXProductoTxA> newList;
    ArrayList<UAXProductoTxA> oldList;

    public Tab04_DiffUtilCallBack(ArrayList<UAXProductoTxA> newList, ArrayList<UAXProductoTxA> oldList){
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
        //return newList.get(newItemPosition).getId_Tx() == oldList.get(oldItemPosition).getId_Tx();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        int result = newList.get(newItemPosition).compareTo(oldList.get(oldItemPosition));
        return result == 0;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        UAXProductoTxA newModel = newList.get(newItemPosition);
        UAXProductoTxA oldModel = oldList.get(oldItemPosition);

        Bundle diff = new Bundle();

        if (newModel.getSaldoTotal() != (oldModel.getSaldoTotal())){
            diff.putInt("Saldo", newModel.getSaldoTotal());
        }

        if (diff.size() == 0){
            return null;
        }
        return  diff;
        //return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
