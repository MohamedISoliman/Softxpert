package io.github.mohamedisoliman.softxpert.ui

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import io.github.mohamedisoliman.softxpert.data.entities.Car
import io.github.mohamedisoliman.softxpert.domain.LoadCars
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

/**
 *
 * Created by Mohamed Ibrahim on 2019-12-25.
 */
class CarsViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private var page = 0
    private val data = mutableListOf<Car>()
    private lateinit var success: (List<Car>) -> Unit
    private lateinit var failure: (Throwable) -> Unit

    fun loadCars() {
        compositeDisposable.add(
            LoadCars(++page).invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe({
                    data.addAll(it)
                    if (::success.isInitialized)
                        success(data)
                }, {
                    if (::failure.isInitialized)
                        failure(it)
                })
        )
    }

    fun carsObserver(success: (List<Car>) -> Unit, failure: (Throwable) -> Unit) {
        this.success = success
        this.failure = failure
    }
}