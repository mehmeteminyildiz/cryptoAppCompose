package com.ma.cryptoappcompose.view

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ma.cryptoappcompose.viewmodel.CryptoListViewModel

/**
created by Mehmet E. Yıldız
 **/

@Composable
fun CryptoListScreen(
    navController: NavController,
    viewModel: CryptoListViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colors.secondary
    ) {


    }

}