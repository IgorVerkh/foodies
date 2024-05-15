package com.example.foodies.ui.screens.catalogue.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodies.R
import com.example.foodies.ui.theme.OrangePrimary
import com.example.foodies.ui.theme.PaleGrey

enum class AppBarState {
    SEARCH,
    IDLE
}

@Composable
fun CatalogueTopAppBar(
    state: AppBarState,
    filterBadge: Int,
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit,
    onBackClick: () -> Unit,
    searchText: String,
    onSearchChange: (String) -> Unit,
    onClearSearchText: () -> Unit
) {
    when (state) {
        AppBarState.SEARCH -> SearchTopAppBar(
            text = searchText,
            onValueChange = { onSearchChange(it) },
            onClear = onClearSearchText,
            onBackClick = onBackClick
        )
        AppBarState.IDLE -> DefaultTopAppBar(
            filterBadge = filterBadge,
            onFilterClick = onFilterClick,
            onSearchClick = onSearchClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DefaultTopAppBar(
    filterBadge: Int,
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        navigationIcon = {
            BadgedIconButton(number = filterBadge, onClick = { onFilterClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter), contentDescription = null
                )
            }
        }, title = {
            Image(
                painter = painterResource(R.drawable.ic_appbar_logo), contentDescription = null
            )
        }, actions = {
            IconButton(onClick = { onSearchClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search icon",
                )
            }
        })
}

@Composable
private fun SearchTopAppBar(
    text: String,
    onValueChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = text,
        onValueChange = { onValueChange(it) },
        modifier = modifier.fillMaxWidth(),
        leadingIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrowleft),
                    tint = OrangePrimary,
                    contentDescription = null
                )
            }
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(
                    onClick = { onClear() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cancel),
                        contentDescription = null
                    )
                }
            }
        },
        placeholder = { Text("Найти блюдо") },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedPlaceholderColor = PaleGrey,
            focusedPlaceholderColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CatalogueTopAppBarPreview() {
    var value by remember {
        mutableStateOf("")
    }
    val onClear = {
        value = ""
    }
    var state by remember {
        mutableStateOf(AppBarState.IDLE)
    }
    CatalogueTopAppBar(
        state = state,
        filterBadge = 3,
        onFilterClick = {  },
        onSearchClick = { state = AppBarState.SEARCH },
        onBackClick = { state = AppBarState.IDLE },
        searchText = value,
        onSearchChange = { value = it },
        onClearSearchText = onClear
    )
}