package com.bwei.retrofit.model;


import com.bwei.retrofit.bean.Bean;



/**
 * Created by muhanxi on 17/12/1.
 */

public interface ModelCallBack {

    public void onSuccess(Bean bean);
    public void onFailure(Exception e);


}
