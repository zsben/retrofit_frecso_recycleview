package com.example.retrofit_recycle_test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IRetrofitGet {
    @GET
    Call<Bean> getcall(@Url String url);
}
