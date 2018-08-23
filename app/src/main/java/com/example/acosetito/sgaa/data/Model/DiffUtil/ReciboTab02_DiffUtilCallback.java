package com.example.acosetito.sgaa.data.Model.DiffUtil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;

import java.util.List;

public class ReciboTab02_DiffUtilCallback extends DiffUtil.Callback{

    private final List<ListarDetalleTx> oldList;
    private final List<ListarDetalleTx> newList;

    public ReciboTab02_DiffUtilCallback(List<ListarDetalleTx> oldList, List<ListarDetalleTx> newList){
        this.oldList = oldList;
        this.newList = newList;
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
        ListarDetalleTx newModel = newList.get(newItemPosition);
        ListarDetalleTx oldModel = oldList.get(oldItemPosition);

        Bundle diff = new Bundle();

        if (newModel.getCodigo().equals(oldModel.getCodigo())){
            diff.putString("Codigo", newModel.getCodigo());
        }

        if(newModel.getLote().equals(oldModel.getLote())){
            diff.putString("Lote", newModel.getLote());
        }

        if (newModel.getDescripcion().equals(oldModel.getDescripcion())){
            diff.putString("Descripcion", newModel.getDescripcion());
        }

        if (newModel.getNombreSubAlmacen().equals(oldModel.getNombreSubAlmacen())){
            diff.putString("SubAlmacen", newModel.getNombreSubAlmacen());
        }

        if (newModel.getCantidadPedida().equals(oldModel.getCantidadPedida())){
            diff.putDouble("CantidadPedida", newModel.getCantidadPedida());
        }

        if (newModel.getCantidadOperacion().equals(oldModel.getCantidadOperacion())){
            diff.putDouble("CantidadOperada", newModel.getCantidadOperacion());
        }

        if (newModel.getSaldo().equals(oldModel.getSaldo())){
            diff.putDouble("Saldo", newModel.getSaldo());
        }

        if (diff.size() == 0){
            return null;
        }
        return  diff;
    }
}
