package com.example.foodies.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    val name: String
)

val categoriesList = listOf(
    Category(0, "Все"),
    Category(676153, "Горячие блюда"),
    Category(676154, "Суши"),
    Category(676155, "Соусы"),
    Category(676156, "Детское меню"),
    Category(676157, "Подарочные сертификаты"),
    Category(676159, "Напитки"),
    Category(676160, "Горячие закуски"),
    Category(676161, "Готовим дома"),
    Category(676162, "Средства индивидуальной защиты"),
    Category(676163, "Салаты"),
    Category(676164, "Супы"),
    Category(676165, "Десерты"),
    Category(676166, "Вок"),
    Category(676167, "Бургеры"),
    Category(676168, "Роллы"),
    Category(676169, "Наборы"),
    Category(676170, "Сашими"),
    Category(676171, "Половинки роллов"),
    Category(676172, "Сувениры"),
    Category(676173, "Бизнес ланчи"),
    Category(1512275, "Фестиваль гёдза"),
    Category(1667058, "Мероприятия")
)

