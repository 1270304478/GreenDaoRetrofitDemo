package com.bwei.retrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bwei.retrofit.EventBean;
import com.bwei.retrofit.Main2Activity;
import com.bwei.retrofit.R;
import com.bwei.retrofit.ResultsBean;
import com.bwei.retrofit.bean.Bean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by muhanxi on 17/12/1.
 */

public class IApdater extends RecyclerView.Adapter<IApdater.IViewHolder> {

    Context context;

    List<ResultsBean> list ;


    public IApdater(Context context) {
        this.context = context;
    }


    public void addData(Bean bean){
        if(list == null){
            list = new ArrayList<>();
        }
        list.addAll(bean.getResults());
        notifyDataSetChanged();
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = View.inflate(context, R.layout.list_item, null);

        return new IViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IViewHolder holder, final int position) {


      holder.adapterSimpledraweeview.setImageURI(list.get(position).getUrl());
        holder.adapterTextview.setText(list.get(position).getCreatedAt());

        holder.adapterTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           EventBus.getDefault().postSticky(new EventBean(list.get(position).getUrl(),list.get(position).getCreatedAt()));


               context.startActivity(new Intent(context, Main2Activity.class));

            }
        });


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class IViewHolder  extends RecyclerView.ViewHolder{
        @BindView(R.id.adapter_simpledraweeview)
        SimpleDraweeView adapterSimpledraweeview;
        @BindView(R.id.adapter_textview)
        TextView adapterTextview;

        IViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
