package com.example.foodies.ui.screens.product_details.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodies.ui.theme.PaleGrey

@Composable
internal fun NutritionListItem(
    name: String,
    value: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.height(50.dp)
    ) {
        Row {
            Text(
                text = name,
                color = PaleGrey,
                fontSize = 17.sp,
                modifier = Modifier.padding(all = 16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = value,
                fontSize = 17.sp,
                modifier = Modifier.padding(all = 16.dp)
            )
        }
    }
}