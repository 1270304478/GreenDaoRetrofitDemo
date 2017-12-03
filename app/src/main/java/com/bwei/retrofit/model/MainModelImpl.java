package com.bwei.retrofit.model;


import com.bwei.retrofit.IApplication;
import com.bwei.retrofit.bean.Bean;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by muhanxi on 17/12/1.
 */

public class MainModelImpl {


    /**
     * get 请求
     * @param callBack
     */
    public void getData(final ModelCallBack callBack){

       Call<Bean> call =  IApplication.iGetDataBase.get(10);

        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {

              Bean bean =   response.body();
                callBack.onSuccess(bean);

                // 保存到数据库
                IApplication.session.getResultsBeanDao().insertInTx(bean.getResults());
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {
                callBack.onFailure(new Exception(""));
            }
        });

    }


    /**
     * post 请求
     */
   /* public void postData(final ModelCallBack callBack){

       Call<Bean> call =  IApplication.iGetDataBase.post("/10/1");

       call.enqueue(new Callback<Bean>() {
           @Override
           public void onResponse(Call<Bean> call, Response<Bean> response) {
               Bean bean = response.body() ;
                callBack.onSuccess(bean);

           }

           @Override
           public void onFailure(Call<Bean> call, Throwable t) {

               callBack.onFailure(new Exception(""));
           }
       });

    }*/

}
