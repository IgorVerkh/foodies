package com.example.foodies.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val id: Int,
    val name: String
)

val tagsList = listOf(
    Tag(1, "Новинка"),
    Tag(2, "Вегетарианское блюдо"),
    Tag(3, "Хит!"),
    Tag(4, "Острое"),
    Tag(5, "Экспресс-меню")
)

