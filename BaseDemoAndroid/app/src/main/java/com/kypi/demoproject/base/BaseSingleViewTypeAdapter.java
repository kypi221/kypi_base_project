package com.kypi.demoproject.base;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSingleViewTypeAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {

    protected List<T> listItems;
    protected final Context context;
    protected final View.OnClickListener onClickListener;

    public BaseSingleViewTypeAdapter(List<T> listItems, Context context, View.OnClickListener onClickListener) {
        this.listItems = listItems;
        this.context = context;
        this.onClickListener = onClickListener;
    }


    public void updateListBookInfo(List<T> items){
        if(items == null){
            return;
        }
        listItems = items;
        notifyDataSetChanged();
    }

    public void addMore(List<T> items){
        if(items == null){
            return;
        }
        listItems.addAll(items);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Kiểu item
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(getLayoutId(), parent, false);
        return createViewViewHolder(itemView);
    }


    protected abstract VH createViewViewHolder(View view);
    protected abstract int getLayoutId();

    @Override
    public int getItemCount() {
        if (listItems == null) {
            listItems = new ArrayList<>();
        }
        return listItems.size();
    }

}
