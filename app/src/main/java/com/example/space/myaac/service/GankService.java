package com.example.space.myaac.service;

import com.example.space.myaac.entity.BaseEntity;
import com.example.space.myaac.entity.GankEntity;
import com.example.space.myaac.entity.ResultBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by space on 2018/3/20.
 */

public interface GankService {

    @GET("/api/data/Android/10/1")
    Call<BaseEntity<ResultBean>> getGank();
}
