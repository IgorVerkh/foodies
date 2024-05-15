package com.example.foodies.ui.screens.product_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.foodies.R
import com.example.foodies.data.model.Product
import com.example.foodies.ui.navigation.Screen
import com.example.foodies.ui.screens.product_details.widgets.NutritionListItem
import com.example.foodies.ui.theme.OrangePrimary
import com.example.foodies.ui.theme.PaleGrey

@Composable
fun ProductDetailsScreen(
    id: Int,
    navController: NavHostController,
    viewModel: ProductDetailsViewModel = hiltViewModel()
) {
    val onAddToProductCart = {
        viewModel.addProductToCart(id)
        navController.navigate(Screen.ProductCart.route) {
            popUpTo(Screen.Catalogue.route)
        }
    }

    ProductDetailsContent(
        viewModel.getProductById(id),
        onNavigateUpButtonClick = { navController.navigateUp() },
        onAddToProductCart = { onAddToProductCart() }
    )
}

@Composable
fun ProductDetailsContent(
    product: Product,
    onNavigateUpButtonClick: () -> Unit,
    onAddToProductCart: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f, fill = false)
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = null,
                    error = painterResource(id = R.drawable.default_product_image),
                    // placeholder is here only for the preview
                    placeholder = painterResource(id = R.drawable.default_product_image),
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = product.name,
                    fontSize = 34.sp,
                    lineHeight = 36.sp,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )
                Text(
                    text = product.description,
                    color = PaleGrey,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.padding(top = 24.dp)
                )
                NutritionListItem(
                    name = "Вес",
                    value = "${product.measure} ${product.measureUnit}"
                )
                HorizontalDivider()
                NutritionListItem(
                    name = "Энерг. ценность",
                    value = "${product.energyPer100Grams} ккал"
                )
                HorizontalDivider()
                NutritionListItem(
                    name = "Белки",
                    value = "${product.proteinsPer100Grams} г"
                )
                HorizontalDivider()
                NutritionListItem(
                    name = "Жиры",
                    value = "${product.fatsPer100Grams} г"
                )
                HorizontalDivider()
                NutritionListItem(
                    name = "Углеводы",
                    value = "${product.carbohydratesPer100Grams} г"
                )
            }
            Button(
                shape = RoundedCornerShape(8.dp),
                onClick = { onAddToProductCart() },
                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "В корзину за ${product.priceCurrent.div(100)} ₽")
            }
        }
        Button(
            shape = CircleShape,
            onClick = { onNavigateUpButtonClick() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .padding(16.dp)
                .size(44.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrowleft),
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ProductDetailsPreview() {
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
    ProductDetailsContent(
        product = product,
        onNavigateUpButtonClick = {},
        onAddToProductCart = {}
    )
}



