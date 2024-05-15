package com.example.foodies.ui.screens.catalogue.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodies.data.model.Product
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap

@Composable
fun ProductGrid(
    products: ImmutableList<Product>,
    productsCart: ImmutableMap<Product, Int>,
    onProductClick: (Int) -> Unit,
    onAddProduct: (Int) -> Unit,
    onRemoveProduct: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 200.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products){product ->
            ProductItem(
                product = product,
                onProductClick = onProductClick,
                onPriceButtonClick = onAddProduct,
                onRemoveClick = onRemoveProduct,
                onAddClick = onAddProduct,
                quantity = productsCart.getOrDefault(product, 0)
            )
        }
    }
}