package com.ma.cryptoappcompose.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.ma.cryptoappcompose.data.CryptoDetail
import com.ma.cryptoappcompose.util.Resource
import com.ma.cryptoappcompose.viewmodel.CryptoDetailViewModel

/**
created by Mehmet E. Yıldız
 **/

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CryptoDetailScreen(
    id: String,
    price: String,
    navController: NavController,
    viewModel: CryptoDetailViewModel = hiltViewModel()
) {

    /*
    // Step 1 -> Yanlış Yöntem: bu şekilde yapınca sürekli olarak istek atıyo...
    // rememberCoroutineScope'u butona tıklayınca yapılacak işlemlerde kullanmalıyız yani 1 kez çalışacak işlemlerde
    val scope = rememberCoroutineScope()
    var cryptoItem by remember {
        mutableStateOf<Resource<CryptoDetail>>(Resource.Loading())
    }

    scope.launch {
        cryptoItem = viewModel.getDetail(id)
        println("data : ${cryptoItem.data}")
    }
*/

    /*
    Step1 den çok daha iyi... Ama daha iyi yollar da var :D
    // Step 2 -> Doğru Yöntem: LaunchEffect kullanmalıyız.
    // key1: ne değişirse baştan recompose edeyim diye soruyor
    var cryptoItem by remember {
        mutableStateOf<Resource<CryptoDetail>>(Resource.Loading())
    }

    LaunchedEffect(key1 = Unit) {
        cryptoItem = viewModel.getDetail(id)
        println("data : ${cryptoItem.data}")
    }
*/
    // step 3 -> Daha doğru yöntem
    val cryptoItem = produceState<Resource<CryptoDetail>>(initialValue = Resource.Loading()) {
        value = viewModel.getDetail(id)
    }.value


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            when (cryptoItem) {
                is Resource.Success -> {
                    val selectedCrypto = cryptoItem.data!![0]
                    Text(
                        text = selectedCrypto.name!!,
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.Center
                    )

                    Image(
                        painter = rememberImagePainter(data = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Bitcoin.svg/1200px-Bitcoin.svg.png"),
                        contentDescription = selectedCrypto.name,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(200.dp, 200.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )

                    Text(
                        text = price,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primaryVariant,
                        textAlign = TextAlign.Center
                    )
                }
                is Resource.Error -> {
                    Text(text = cryptoItem.message!!)
                }
                is Resource.Loading -> {
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)
                }
            }

        }
    }

}