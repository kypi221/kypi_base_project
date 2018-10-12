package com.kypi.demoproject.mvp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kypi.demoproject.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleAdapter extends RecyclerView.Adapter {

    private List<String> listItems;
    private View.OnClickListener onClickListener;

    public SimpleAdapter(List<String> listItems, View.OnClickListener onClickListener) {
        this.listItems = listItems;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Kiểu item
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_list_simple, parent, false);
        ItemViewHolder holder = new ItemViewHolder(itemView);
        itemView.setOnClickListener(onClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder ) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            String item = listItems.get(position);
            itemViewHolder.tvSimpleText.setText(item);
        }
    }

    @Override
    public int getItemCount() {
        if(listItems == null){
            listItems = new ArrayList<>();
        }
        return listItems.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_simple_text)
        TextView tvSimpleText;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
