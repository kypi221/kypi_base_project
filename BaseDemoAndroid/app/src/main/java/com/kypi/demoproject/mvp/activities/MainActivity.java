package com.kypi.demoproject.mvp.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kypi.demoproject.R;
import com.kypi.demoproject.app.helper.ViewClickedHelper;
import com.kypi.demoproject.base.BaseActivity;
import com.kypi.demoproject.base.BaseSingleViewTypeAdapter;
import com.kypi.demoproject.di.component.ActivityComponent;
import com.kypi.demoproject.mvp.features.sendtext.SendTextDialog;
import com.kypi.demoproject.base.MyCustomToast;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    private FeatureObject[] features = new FeatureObject[]{
      new FeatureObject("IRead Demo API", IReadDemoActivity.class)
    };
    private FeatureAdapter adapter;


    /**
     * get layoutEyeProtection to inflate
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * Setup activity component/inject
     *
     * @param mActivityComponent
     */
    @Override
    protected void setupActivityComponent(ActivityComponent mActivityComponent) {
        mActivityComponent.inject(this);
    }

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FeatureAdapter(Arrays.asList(features), this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }


    @Override
    public void onClick(View v) {
//
//        FeatureObject item = (FeatureObject) v.getTag(R.id.tag_object);
//        startActivity(new Intent(this, item.aClass));

        ILog.d("Main Activty Clicked");
        SendTextDialog.showMe(this, (text, dialog) -> showMessage(text, MyCustomToast.ToastType.SUCCESS));

    }

    public class FeatureAdapter extends BaseSingleViewTypeAdapter<FeatureAdapter.ItemViewHolder, FeatureObject> {


        public FeatureAdapter(List<FeatureObject> listItems, Context context, View.OnClickListener onClickListener) {
            super(listItems, context, onClickListener);
        }

        @Override
        public void onBindViewHolder(@NonNull FeatureAdapter.ItemViewHolder holder, int position) {
            FeatureObject item = listItems.get(position);
            holder.tvSimpleText.setText(item.name);

            ViewClickedHelper.setClickForView(holder.itemView, item, onClickListener, null);
        }

        @Override
        protected FeatureAdapter.ItemViewHolder createViewViewHolder(View view) {
            return new FeatureAdapter.ItemViewHolder(view);
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



    public class FeatureObject{
        final String name;
        final Class aClass;

        public FeatureObject(String name, Class aClass) {
            this.name = name;
            this.aClass = aClass;
        }
    }
}
