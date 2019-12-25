package io.github.mohamedisoliman.softxpert.data.entities

data class Car(
    val brand: String? = "",
    val constructionYear: String? = "",
    val id: Int? = 0,
    val imageUrl: String? = "",
    val isUsed: Boolean? = false
)