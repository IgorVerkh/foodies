package com.example.foodies.ui.screens.catalogue.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodies.ui.theme.OrangePrimary

@Composable
fun FixedCartButton(
    total: Int,
    onClick: () -> Unit,
) {
    Surface(
        shadowElevation = 4.dp
    ) {
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxWidth()
        ) {
            Text(text = "В корзину за $total ₽")
        }
    }
}