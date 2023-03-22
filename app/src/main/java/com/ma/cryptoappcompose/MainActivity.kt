package com.ma.cryptoappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.ma.cryptoappcompose.ui.theme.CryptoAppComposeTheme
import com.ma.cryptoappcompose.view.CryptoDetailScreen
import com.ma.cryptoappcompose.view.CryptoListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoAppComposeTheme {
                // remember olmasının sebebi, kimi durumda tekrar initialize edilmesin diye
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "crypto_list_screen") {
                    composable("crypto_list_screen") {
                        // CryptListScreen
                        CryptoListScreen(navController = navController)
                    }
                    composable(
                        "crypto_detail_screen/{cryptoId}/{cryptoPrice}",
                        arguments = listOf(
                            navArgument("cryptoId") {
                                type = NavType.StringType
                            },
                            navArgument("cryptoPrice") {
                                type = NavType.StringType
                            }
                        ),
                    ) {
                        val cryptoId = remember {
                            it.arguments?.getString("cryptoId")
                        }
                        val cryptoPrice = remember {
                            it.arguments?.getString("cryptoPrice")
                        }
                        cryptoId?.let { id ->
                            cryptoPrice?.let { price ->
                                CryptoDetailScreen(
                                    id = id,
                                    price = price,
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
