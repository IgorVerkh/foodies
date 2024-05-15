package com.example.foodies.ui.screens.catalogue

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.foodies.data.model.Category
import com.example.foodies.data.model.Product
import com.example.foodies.ui.navigation.Screen
import com.example.foodies.ui.screens.catalogue.widgets.AppBarState
import com.example.foodies.ui.screens.catalogue.widgets.BottomSheet
import com.example.foodies.ui.screens.catalogue.widgets.CatalogueTopAppBar
import com.example.foodies.ui.screens.catalogue.widgets.CategoriesRow
import com.example.foodies.ui.screens.catalogue.widgets.FixedCartButton
import com.example.foodies.ui.screens.catalogue.widgets.ProductGrid
import com.example.foodies.ui.util.booleanToInt
import com.example.foodies.ui.util.countTotal
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap

@Composable
fun CatalogueScreen(
    navController: NavHostController,
    viewModel: CatalogueViewModel = hiltViewModel()
) {
    viewModel.refreshProductCart()
    // TODO: Remake the whole screen state pipeline with the State class
    var productList = viewModel.productList.collectAsStateWithLifecycle().value
    val categoriesList = viewModel.categoryList.collectAsStateWithLifecycle().value
    val productCart = viewModel.productCart.collectAsStateWithLifecycle().value

    var activeCategory by remember { mutableStateOf(categoriesList[0]) }
    var total by remember { mutableIntStateOf(countTotal(productCart)) }
    var showFixedCartButton by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var filterBadge by remember { mutableIntStateOf(0) }
    var appBarState by remember { mutableStateOf(AppBarState.IDLE) }
    var searchText by remember { mutableStateOf("") }
    // TODO: Pack into a Map (would be simpler with a UI data class)
    var noMeat by remember { mutableStateOf(false) }
    var spicy by remember { mutableStateOf(false) }
    var discount by remember { mutableStateOf(false) }

    LaunchedEffect(productCart) {
        total = countTotal(productCart)
        showFixedCartButton = total > 0
    }

    val onCategoryChange = { category: Category ->
        activeCategory = if (category != activeCategory) category else categoriesList[0]
        viewModel.setCategoryFilter(activeCategory)
    }
    val onProductCardClick = { id: Int ->
        navController.navigate("${Screen.ProductDetails.route}/$id")
    }
    val onNoMeatChange = {
        noMeat = !noMeat
        filterBadge = booleanToInt(noMeat) + booleanToInt(spicy) + booleanToInt(discount)
    }
    val onSpicyChange = {
        spicy = !spicy
        filterBadge = booleanToInt(noMeat) + booleanToInt(spicy) + booleanToInt(discount)
    }
    val onDiscountChange = {
        discount = !discount
        filterBadge = booleanToInt(noMeat) + booleanToInt(spicy) + booleanToInt(discount)
    }

    CatalogueContent(
        categories = categoriesList.toImmutableList(),
        products = productList.filter { it.name.lowercase().contains(searchText.lowercase()) }.toImmutableList(),
        productsCart = productCart.toImmutableMap(),
        activeCategory = activeCategory,
        total = total,
        filterBadge = filterBadge,
        noMeat = noMeat,
        spicy = spicy,
        discount = discount,
        appBarState = appBarState,
        searchText = searchText,
        showFixedCartButton = showFixedCartButton,
        showBottomSheet = showBottomSheet,
        onCategoryChange = onCategoryChange,
        onProductClick = onProductCardClick,
        onAddProduct = { id: Int -> viewModel.addProductToCart(id) },
        onRemoveProduct = { id: Int -> viewModel.removeProductFromCart(id) },
        onFilterClick = { showBottomSheet = !showBottomSheet },
        onSearchClick = { appBarState = AppBarState.SEARCH },
        onBackClick = { appBarState = AppBarState.IDLE; searchText = "" },
        onClearSearchText = { searchText = "" },
        onSearchChange = { searchText = it },
        onCartButtonClick = { navController.navigate(Screen.ProductCart.route) },
        onDismissBottomSheet = { showBottomSheet = false },
        onNoMeatChange = onNoMeatChange,
        onSpicyChange = onSpicyChange,
        onDiscountChange = onDiscountChange
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CatalogueContent(
    categories: ImmutableList<Category>,
    products: ImmutableList<Product>,
    productsCart: ImmutableMap<Product, Int>,
    activeCategory: Category,
    total: Int,
    filterBadge: Int,
    noMeat: Boolean,
    spicy: Boolean,
    discount: Boolean,
    showFixedCartButton: Boolean,
    showBottomSheet: Boolean,
    appBarState: AppBarState,
    searchText: String,
    onCategoryChange: (category: Category) -> Unit,
    onAddProduct: (Int) -> Unit,
    onRemoveProduct: (Int) -> Unit,
    onProductClick: (Int) -> Unit,
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit,
    onBackClick: () -> Unit,
    onClearSearchText: () -> Unit,
    onSearchChange: (String) -> Unit,
    onCartButtonClick: () -> Unit,
    onDismissBottomSheet: () -> Unit,
    onNoMeatChange: () -> Unit,
    onSpicyChange: () -> Unit,
    onDiscountChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f, fill = false)
            ) {
                Surface(
                    shadowElevation = 4.dp,
                    modifier = Modifier.padding(bottom = 1.dp)
                ) {
                    Column {
                        CatalogueTopAppBar(
                            state = appBarState,
                            filterBadge = filterBadge,
                            onFilterClick = onFilterClick,
                            onSearchClick = onSearchClick,
                            searchText = searchText,
                            onBackClick = onBackClick,
                            onClearSearchText = onClearSearchText,
                            onSearchChange = onSearchChange
                        )
                        CategoriesRow(
                            categories = categories,
                            selectedCategory = activeCategory,
                            onCategoryChange = onCategoryChange
                        )
                    }
                }
                ProductGrid(
                    products = products,
                    productsCart = productsCart,
                    onProductClick = onProductClick,
                    onAddProduct = onAddProduct,
                    onRemoveProduct = onRemoveProduct
                )
            }
            if (showFixedCartButton) {
                FixedCartButton(
                    total = total,
                    onClick = onCartButtonClick
                )
            }
            if (showBottomSheet) {
                BottomSheet(
                    noMeat = noMeat,
                    spicy = spicy,
                    discount = discount,
                    onNoMeatChange = { onNoMeatChange() },
                    onSpicyChange = { onSpicyChange() },
                    onDiscountChange = { onDiscountChange() },
                    onDismissRequest = { onDismissBottomSheet() })
            }
        }
    }
}
