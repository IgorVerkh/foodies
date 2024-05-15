package com.example.foodies.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    @SerialName("category_id") val categoryId: Int,
    val name: String,
    val description: String,
    val image: String,
    @SerialName("price_current") val priceCurrent: Int,
    @SerialName("price_old") val priceOld: Int?,
    val measure: Int,
    @SerialName("measure_unit") val measureUnit: String,
    @SerialName("energy_per_100_grams") val energyPer100Grams: Double,
    @SerialName("proteins_per_100_grams") val proteinsPer100Grams: Double,
    @SerialName("fats_per_100_grams") val fatsPer100Grams: Double,
    @SerialName("carbohydrates_per_100_grams") val carbohydratesPer100Grams: Double,
    @SerialName("tag_ids") val tagIds: List<Int>
    )

val productsList = listOf(
    Product(
        1,
        676171,
        "Ролл чеддер 4шт",
        "Урамаки ролл с обожженным сыром чеддер с начинкой из мусса лосося и огурца. Украшается зеленым луком и соусом сладкий чили  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
        "1.jpg",
        21000,
        23035,
        120,
        "г",
        302.7,
        8.5,
        9.1,
        46.8,
        listOf(1)
    ),
    Product(
        19304068,
        676171,
        "Ролл чеддер 4шт",
        "Урамаки ролл с обожженным сыром чеддер с начинкой из мусса лосося и огурца. Украшается зеленым луком и соусом сладкий чили  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
        "1.jpg",
        21000,
        null,
        120,
        "г",
        302.7,
        8.5,
        9.1,
        46.8,
        listOf(1)
    ),
    Product(
        20465350,
        676160,
        "Гедзе с курицей",
        "Традиционные японские пельмени с начинкой из курицы. Подаются с соусом «Гёдзе».",
        "1.jpg",
        24000,
        null,
        140,
        "г",
        269.1,
        7.0,
        18.0,
        19.4,
        listOf()
    ),
    Product(
        20465351,
        676169,
        "Осака",
        "Калифорния 1/2, Филадельфия кунжут 1/2, Унаги маки 1/2 и суши: лосось 2 шт., осьминог 2 шт  На 1-2 персоны  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
        "1.jpg",
        99000,
        170200,
        445,
        "г",
        269.3,
        9.2,
        5.7,
        45.3,
        listOf()
    ),
    Product(
        20465352,
        676169,
        "Киото сет",
        "Набор из роллов: Запеченный ролл с мидией, Запеченный ролл с гребешком, Запеченный ролл с шиитаке На 2-3 персоны  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
        "1.jpg",
        109000,
        148469,
        720,
        "г",
        334.0,
        7.7,
        15.8,
        40.3,
        listOf()
    )
)