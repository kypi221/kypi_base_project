package com.kypi.demoproject.mvp.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.kypi.demoproject.R;
import com.kypi.demoproject.base.BaseSingleViewTypeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleAdapter extends BaseSingleViewTypeAdapter<SimpleAdapter.ItemViewHolder, String> {


    public SimpleAdapter(List<String> listItems, Context context, View.OnClickListener onClickListener) {
        super(listItems, context, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String item = listItems.get(position);
        holder.tvSimpleText.setText(item);
    }

    @Override
    protected ItemViewHolder createViewViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_list_simple;
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
