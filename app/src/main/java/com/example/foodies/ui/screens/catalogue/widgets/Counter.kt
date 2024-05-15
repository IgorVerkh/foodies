package com.example.foodies.ui.screens.catalogue.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodies.R
import com.example.foodies.ui.theme.FoodiesTheme
import com.example.foodies.ui.theme.OrangePrimary

@Composable
internal fun Counter(
    onRemoveClick: () -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
    quantity: Int = 0,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Color.White),
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = { onRemoveClick() },
            colors = colors,
            elevation = elevation,
            modifier = Modifier.size(40.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_minus),
                contentDescription = null,
                tint = OrangePrimary
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = quantity.toString(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = { onAddClick() },
            colors = colors,
            elevation = elevation,
            modifier = Modifier.size(40.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = null,
                tint = OrangePrimary
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CounterPreview() {
    FoodiesTheme {
        Box(modifier = Modifier
            .width(135.dp)
            .padding(all = 8.dp)
        ) {
            Counter(
                onRemoveClick = { /*TODO*/ },
                onAddClick = { /*TODO*/ }
            )
        }
    }
}