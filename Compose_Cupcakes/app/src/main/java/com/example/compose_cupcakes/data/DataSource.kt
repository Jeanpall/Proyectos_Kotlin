package com.example.compose_cupcakes.data

import com.example.compose_cupcakes.R

object DataSource {
    val flavors = listOf(
        R.string.vainilla,
        R.string.chocolate,
        R.string.arequipe,
        R.string.salted_caramel,
        R.string.coffee
    )

    val quantityOptions = listOf(
        Pair(R.string.one_cupcakes, 1),
        Pair(R.string.six_cupcakes, 6),
        Pair(R.string.twelve_cupcakes, 12)
    )
}