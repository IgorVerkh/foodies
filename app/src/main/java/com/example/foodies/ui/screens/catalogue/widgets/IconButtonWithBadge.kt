package com.example.foodies.ui.screens.catalogue.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodies.R
import com.example.foodies.ui.theme.OrangePrimary

@Composable
fun BadgedIconButton(
    number: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    BadgedBox(
        badge = {
            if (number > 0)
            FoodiesBadge(number)
        }
    ) {
        IconButton(
            onClick = { onClick() },
            modifier = modifier,
            content = content
        )
    }
}

@Composable
fun FoodiesBadge(
    number: Int
) {
    Badge(
        containerColor = OrangePrimary,
        contentColor = Color.White,
        modifier = Modifier.offset(x = (-9).dp, y = 4.dp)
    ) {
        Text(text = number.toString())
    }
}

@Preview
@Composable
private fun BadgedIconButtonPreview() {
    BadgedIconButton(3, {}) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter), contentDescription = null
        )
    }
}