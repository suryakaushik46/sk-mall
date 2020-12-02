package com.example.skmall;

import com.example.skmall.Models.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitClient {
    @POST("posts")
    Call<Order> gotoFakePayment(@Body Order order);
}
