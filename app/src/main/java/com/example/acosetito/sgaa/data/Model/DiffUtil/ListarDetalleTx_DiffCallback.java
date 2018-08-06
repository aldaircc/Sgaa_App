package com.example.acosetito.sgaa.data.Model.DiffUtil;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;

import java.util.List;

public class ListarDetalleTx_DiffCallback extends DiffUtil.Callback{

    private final List<ListarDetalleTx> mOldList;
    private final List<ListarDetalleTx> mNewList;

    public ListarDetalleTx_DiffCallback(List<ListarDetalleTx> oldList, List<ListarDetalleTx> newList){
        this.mOldList = oldList;
        this.mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).getId_Tx() == mNewList.get(newItemPosition).getId_Tx();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final ListarDetalleTx oldItem = mOldList.get(oldItemPosition);
        final ListarDetalleTx newItem = mNewList.get(newItemPosition);
        return oldItem.getId_Tx().equals(newItem.getId_Tx());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
