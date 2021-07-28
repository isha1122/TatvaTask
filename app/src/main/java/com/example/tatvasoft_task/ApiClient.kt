package com.example.tatvasoft_task

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "https://reqres.in/api/"
    fun getOkHttpClient(language: String?): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
            .connectTimeout(180, TimeUnit.SECONDS)
            .build()
    }

    private var retrofit: Retrofit? = null

    //      .header("User-Agent", "Cabera")
    val client: Retrofit?
        get() {
            val interceptor = Interceptor { chain ->
                val original = chain.request()
                val newRequest =
                    chain.request().newBuilder() //      .header("User-Agent", "Cabera")
                        .method(original.method(), original.body())
                        .build()
                chain.proceed(newRequest)
            }
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(interceptor)
            val httpClient = builder.build()
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }
}