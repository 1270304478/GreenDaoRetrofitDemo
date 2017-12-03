package com.bwei.retrofit;

import com.bwei.retrofit.bean.Bean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/1 14:55
 */
//http://v.juhe.cn/toutiao/index?type="+data+"&key=2f092bd9ce76c0257052d6d3c93c11b4
//http://gank.io/api/data/福利/10/1
public interface IGetDataBase {
    @GET("api/data/福利/{key}/1")
    Call<Bean> get(@Path("key") int key);
/**
 * post请求
 *
 */
@FormUrlEncoded
    @POST("/api/data/福利")
    Call<Bean> post(@Field("key") String key);
}
