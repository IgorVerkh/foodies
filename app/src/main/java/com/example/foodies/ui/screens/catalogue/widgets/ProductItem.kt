package com.example.foodies.ui.screens.catalogue.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.foodies.R
import com.example.foodies.data.model.Product
import com.example.foodies.ui.theme.GreyBackground
import com.example.foodies.ui.theme.PaleGrey

@Composable
fun ProductItem(
    product: Product,
    onProductClick: (Int) -> Unit,
    onPriceButtonClick: (Int) -> Unit,
    onRemoveClick: (Int) -> Unit,
    onAddClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    tagId: Int? = null,
    quantity: Int = 0
) {

    Surface(
        color = GreyBackground,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .widthIn(0.dp, 200.dp)
            .height(350.dp)
            .padding(8.dp)
            .clickable { onProductClick(product.id) }
    ) {
        Column {
            Box {
                AsyncImage(
                    model = product.image,
                    contentDescription = null,
                    error = painterResource(id = R.drawable.default_product_image),
                    // placeholder is here only for the preview
                    placeholder = painterResource(id = R.drawable.default_product_image),
                    modifier = Modifier.size(200.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_tag_discount),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
            // Magically pulls down everything below
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = product.name,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            Text(
                text = "${product.measure} ${product.measureUnit}",
                fontSize = 14.sp,
                color = PaleGrey,
                lineHeight = 20.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            PriceButtonWithCounter(
                currentPrice = product.priceCurrent.div(100),
                oldPrice = product.priceOld?.div(100),
                onPriceButtonClick = { onPriceButtonClick(product.id) },
                onRemoveClick = { onRemoveClick(product.id) },
                onAddClick = { onAddClick(product.id) },
                quantity = quantity)
        }
    }
}

@Composable
private fun PriceButtonWithCounter(
    currentPrice: Int,
    onPriceButtonClick: () -> Unit,
    onRemoveClick: () -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
    oldPrice: Int? = null,
    quantity: Int = 0
) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
            .height(40.dp)
            .clickable(enabled = false, onClick = {}),
    ) {
        if (quantity == 0) {
            PriceButton(
                currentPrice = currentPrice,
                oldPrice = oldPrice,
                onClick = onPriceButtonClick
            )
        } else {
            Counter(
                onRemoveClick = onRemoveClick,
                onAddClick = onAddClick,
                quantity = quantity
            )
        }
    }

}

@Preview
@Composable
private fun ProductItemPreview() {
    val product = Product(
        id = 18,
        categoryId = 676154,
        name = "Суши с осьминогом",
        description = "Осьминог  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
        image = "1.jpg",
        priceCurrent = 13000,
        priceOld = null,
        measure = 30,
        measureUnit = "g",
        energyPer100Grams = 204.3,
        carbohydratesPer100Grams = 38.2,
        fatsPer100Grams = 0.3,
        proteinsPer100Grams = 12.2,
        tagIds = listOf()
    )
    var quantity by remember { mutableIntStateOf(100) }
    ProductItem(
        product = product,
        onProductClick = { },
        onPriceButtonClick = { quantity += 1 },
        onRemoveClick = { quantity -= 1 },
        onAddClick = { quantity += 1 },
        quantity = quantity
    )
}
