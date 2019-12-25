package io.github.mohamedisoliman.softxpert.domain

import io.github.mohamedisoliman.softxpert.data.entities.Car
import io.github.mohamedisoliman.softxpert.data.entities.remote.ServerFactory
import io.reactivex.Single

/**
 *
 * Created by Mohamed Ibrahim on 2019-12-25.
 */
class LoadCars(private val page: Int) : () -> Single<List<Car>> {

    override fun invoke(): Single<List<Car>> {
        return ServerFactory.server().loadCars(page).map { it.data }
    }
}