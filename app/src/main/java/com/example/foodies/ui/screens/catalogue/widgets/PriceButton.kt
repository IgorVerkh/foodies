package com.example.foodies.ui.screens.catalogue.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodies.ui.theme.FoodiesTheme
import com.example.foodies.ui.theme.PaleGrey

@Composable
fun PriceButton(
    currentPrice: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    oldPrice: Int? = null,
    currencySymbol: Char = '₽'
) {
    Button(
        shape = RoundedCornerShape(8.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp
        ),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        if (oldPrice == null) {
            Text(
                color = Color.Black,
                text = "$currentPrice $currencySymbol",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        } else {
            Text(
                color = Color.Black,
                text = "$currentPrice $currencySymbol",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                color = PaleGrey,
                text = "$oldPrice $currencySymbol",
                fontSize = 16.sp,
                style = TextStyle(textDecoration = TextDecoration.LineThrough)
            )
        }
    }
}

@Preview
@Composable
private fun PriceButtonPreview() {
    FoodiesTheme {
        PriceButton(
            currentPrice = 1000,
            oldPrice = 1100,
            onClick = {})
    }
}