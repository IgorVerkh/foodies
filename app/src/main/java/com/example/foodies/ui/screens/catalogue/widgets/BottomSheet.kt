package com.example.foodies.ui.screens.catalogue.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodies.ui.theme.OrangePrimary

@ExperimentalMaterial3Api
@Composable
fun BottomSheet(
    noMeat: Boolean,
    spicy: Boolean,
    discount: Boolean,
    onNoMeatChange: () -> Unit,
    onSpicyChange: () -> Unit,
    onDiscountChange: () -> Unit,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(

        onDismissRequest = { onDismissRequest() },
        dragHandle = { Box(modifier = Modifier.height(32.dp)) },
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .heightIn(min = 250.dp)
        ) {
            Text(
                text = "Подобрать блюда",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            ModalFilterItem(title = "Без мяса",
                isChecked = noMeat,
                onCheckedChange = { onNoMeatChange() })
            HorizontalDivider()
            ModalFilterItem(title = "Острое",
                isChecked = spicy,
                onCheckedChange = { onSpicyChange() })
            HorizontalDivider()
            ModalFilterItem(title = "Со скидкой",
                isChecked = discount,
                onCheckedChange = { onDiscountChange() })
        }
    }
}

@Composable
private fun ModalFilterItem(
    title: String,
    isChecked: Boolean,
    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = title, fontSize = 16.sp)
            Spacer(modifier = Modifier.weight(1f))
            Checkbox(
                checked = isChecked,
                onCheckedChange = { onCheckedChange() },
                colors = CheckboxDefaults.colors(checkedColor = OrangePrimary),
                modifier = Modifier.offset(x = 12.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
private fun BottomSheetPreview() {
    BottomSheet(
        noMeat = true,
        spicy = false,
        discount = true,
        onDiscountChange = {},
        onNoMeatChange = {},
        onSpicyChange = {},
        onDismissRequest = {}
    )
}
