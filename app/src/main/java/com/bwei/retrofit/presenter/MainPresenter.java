package com.bwei.retrofit.presenter;


import com.bwei.retrofit.bean.Bean;
import com.bwei.retrofit.model.MainModelImpl;
import com.bwei.retrofit.model.ModelCallBack;
import com.bwei.retrofit.view.IMainView;

/**
 * Created by muhanxi on 17/12/1.
 */

public class MainPresenter {


    private IMainView iView ;
    private MainModelImpl mainModel ;
    public MainPresenter(IMainView view){
        this.iView = view;
        this.mainModel = new MainModelImpl();

    }


    public void get(){

        mainModel.getData(new ModelCallBack() {
            @Override
            public void onSuccess(Bean bean) {

                if(iView != null){
                    iView.onSuccess(bean);
                }
            }

            @Override
            public void onFailure(Exception e) {
                if(iView != null){
                    iView.onFailure(e);
                }
            }
        });
    }

    /*public void post(){
        mainModel.getData(new ModelCallBack() {
            @Override
            public void onSuccess(Bean bean) {
                if(iView != null){
                    iView.onSuccess(bean);
                }
            }

            @Override
            public void onFailure(Exception e) {
                if(iView != null){
                    iView.onFailure(e);
                }
            }
        });
    }*/

}
