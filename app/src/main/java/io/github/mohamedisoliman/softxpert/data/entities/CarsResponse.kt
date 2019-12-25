package io.github.mohamedisoliman.softxpert.data.entities

data class CarsResponse(
    val data: List<Car>? = listOf(),
    val status: Int? = 0
)