package com.example.tatvasoft_task;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("users")
    Call<ResponseBody> callForItems(@Query("page") int page);
}
