package com.bwei.retrofit.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwei.retrofit.IApplication;
import com.bwei.retrofit.R;
import com.bwei.retrofit.ResultsBean;
import com.bwei.retrofit.adapter.IApdater;
import com.bwei.retrofit.bean.Bean;
import com.bwei.retrofit.presenter.MainPresenter;
import com.bwei.retrofit.view.IMainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.bwei.retrofit.MainActivity.isNetworkAvailable;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/2 11:11
 */
public class Fragment01 extends Fragment implements IMainView {
    private MainPresenter presenter;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    private IApdater adapter;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment01, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new MainPresenter(this);
        presenter.get();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        adapter = new IApdater(getActivity());

        boolean b = isNetworkAvailable(getActivity());
        if (b){
            recycleview.setLayoutManager(manager);
            recycleview.setAdapter(adapter);
            List<ResultsBean> dataBeans = IApplication.session.getResultsBeanDao().loadAll();
            for (ResultsBean bean : dataBeans) {
                System.out.println(bean.toString());
            }
        }else{
            Toast.makeText(getActivity(),"当前网络不可用，请检查网络！",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(Bean bean) {
        adapter.addData(bean);
    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
