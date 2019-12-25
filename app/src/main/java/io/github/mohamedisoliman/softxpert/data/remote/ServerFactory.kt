package io.github.mohamedisoliman.softxpert.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * Created by Mohamed Ibrahim on 2019-12-25.
 */
object ServerFactory {

    private const val BASE_URL = "http://demo1286023.mockable.io/api/v1/"

    fun server(): SoftxpertApi {

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(SoftxpertApi::class.java)
    }

    private val okHttpClient: OkHttpClient
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val clientBuilder = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            return clientBuilder.build()
        }
}