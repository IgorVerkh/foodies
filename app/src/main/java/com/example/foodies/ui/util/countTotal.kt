package com.example.foodies.ui.util

import com.example.foodies.data.model.Product

fun countTotal(cart: Map<Product, Int>): Int {
    if (cart.isEmpty()) {
        return 0
    }
    var total = 0
    for ((product, quantity) in cart) {
        total += product.priceCurrent.div(100) * quantity
    }
    return total
}