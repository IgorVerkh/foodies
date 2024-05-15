package com.example.foodies.ui.screens.shopping_cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.foodies.R
import com.example.foodies.data.model.Product
import com.example.foodies.ui.screens.shopping_cart.widgets.CartListItem
import com.example.foodies.ui.theme.OrangePrimary
import com.example.foodies.ui.util.countTotal
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.toImmutableMap

@Composable
fun ProductCartScreen(
    navController: NavHostController,
    viewModel: ProductCartViewModel = hiltViewModel()
) {
    viewModel.refreshProductCart()
    val productCart = viewModel.productCart.collectAsStateWithLifecycle().value
    var isEmpty by remember { mutableStateOf(productCart.isEmpty()) }
    var total by remember { mutableIntStateOf(countTotal(productCart)) }

    LaunchedEffect(productCart) {
        isEmpty = productCart.isEmpty()
        total = countTotal(productCart)
    }

    val onAddProductToCart = { id: Int ->
        viewModel.addProductToCart(id)
    }
    val onRemoveProductFromCart = { id: Int ->
        viewModel.removeProductFromCart(id)
    }

    ProductCartContent(
        products = productCart.toImmutableMap(),
        isEmpty = isEmpty,
        total = total,
        onAddProductToCart = onAddProductToCart,
        onRemoveProductFromCart = onRemoveProductFromCart,
        onNavigateUpButtonClick = { navController.navigateUp() }
    )

}

@Composable
fun ProductCartContent(
    products: ImmutableMap<Product, Int>,
    isEmpty: Boolean,
    total: Int,
    onAddProductToCart: (Int) -> Unit,
    onRemoveProductFromCart: (Int) -> Unit,
    onNavigateUpButtonClick: () -> Unit
) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f, fill = false)
        ) {
            Surface(
                shadowElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { onNavigateUpButtonClick() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrowleft),
                            contentDescription = null,
                            tint = OrangePrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Корзина",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            if (isEmpty) {
                EmptyCart()
            } else {
                ProductsList(
                    products = products,
                    onAddProductToCart = onAddProductToCart,
                    onRemoveProductFromCart = onRemoveProductFromCart
                )
            }
        }
        if (!isEmpty) {
            Button(
                shape = RoundedCornerShape(8.dp),
                onClick = {  },
                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Заказать за $total ₽")
            }
        }
    }
}

@Composable
private fun ProductsList(
    products: ImmutableMap<Product, Int>,
    onAddProductToCart: (Int) -> Unit,
    onRemoveProductFromCart: (Int) -> Unit
) {
    LazyColumn {
        products.forEach {
            item {
                CartListItem(
                    product = it.key,
                    quantity = it.value,
                    onAddClick = { onAddProductToCart(it.key.id) },
                    onRemoveClick = { onRemoveProductFromCart(it.key.id) })
            }
        }
    }
}

@Composable
private fun EmptyCart() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Пусто, выберите блюда в каталоге :)")
    }
}
