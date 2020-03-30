package com.anchal.hotnew.network;

import com.anchal.hotnew.base.BaseModel;
import com.anchal.hotnew.base.BaseResponse;
import com.anchal.hotnew.home.hottab.HotModel;
import com.anchal.hotnew.home.newtab.NewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("hot.json")
    Call<BaseResponse<BaseModel<List<HotModel>>>> getHotList();

    @GET("new.json")
    Call<BaseResponse<BaseModel<List<NewModel>>>> getNewList();
}
