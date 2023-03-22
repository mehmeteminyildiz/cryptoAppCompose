package com.ma.cryptoappcompose.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

/**
created by Mehmet E. Yıldız
 **/

@Composable
fun CryptoDetailScreen(
    id: String,
    price: String,
    navController: NavController
) {
    Text(text = "detail")
}