package com.example.foodies.ui.screens.shopping_cart.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.foodies.R
import com.example.foodies.data.model.Product
import com.example.foodies.ui.screens.catalogue.widgets.Counter
import com.example.foodies.ui.theme.GreyBackground
import com.example.foodies.ui.theme.PaleGrey

@Composable
fun CartListItem(
    product: Product,
    quantity: Int,
    onAddClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier.height(128.dp)
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                error = painterResource(id = R.drawable.default_product_image),
                // placeholder is here only for the preview
                placeholder = painterResource(id = R.drawable.default_product_image),
                modifier = Modifier.padding(all = 16.dp)
            )
            Column {
                Text(
                    text = product.name,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(top = 8.dp, end = 48.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(135.dp)
                    ) {
                        Counter(
                            colors = ButtonDefaults.buttonColors(containerColor = GreyBackground),
                            elevation = ButtonDefaults.buttonElevation(),
                            quantity = quantity,
                            onAddClick = { onAddClick() },
                            onRemoveClick = { onRemoveClick() }
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        if (product.priceOld == null) {
                            Text(
                                text = "${product.priceCurrent.div(100) * quantity} ₽",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            Column(
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    text = "${product.priceCurrent.div(100) * quantity} ₽",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = "${product.priceOld.div(100) * quantity} ₽",
                                    fontSize = 14.sp,
                                    color = PaleGrey,
                                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                                )
                            }
                        }
                    }
                }
            }
        }
        HorizontalDivider()
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CartListItemPreview() {
    val product = Product(
        id = 18,
        categoryId = 676154,
        name = "Кусок пиццы с соусом песто и оливками",
        description = "Осьминог  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
        image = "1.jpg",
        priceCurrent = 13000,
        priceOld = 12000,
        measure = 30,
        measureUnit = "g",
        energyPer100Grams = 204.3,
        carbohydratesPer100Grams = 38.2,
        fatsPer100Grams = 0.3,
        proteinsPer100Grams = 12.2,
        tagIds = listOf()
    )
    var quantity by remember { mutableIntStateOf(1) }
    CartListItem(
        product = product,
        quantity = quantity,
        onAddClick = { quantity += 1 },
        onRemoveClick = { quantity -= 1 }
    )
}