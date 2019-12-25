package io.github.mohamedisoliman.softxpert.data.entities.remote

import io.github.mohamedisoliman.softxpert.data.entities.CarsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * Created by Mohamed Ibrahim on 2019-12-25.
 */
interface SoftxpertApi {

    @GET("cars")
    fun loadCars(@Query("page") page: Int): Single<CarsResponse>
}