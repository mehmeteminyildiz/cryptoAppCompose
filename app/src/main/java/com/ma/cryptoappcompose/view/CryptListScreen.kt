package com.ma.cryptoappcompose.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ma.cryptoappcompose.viewmodel.CryptoListViewModel
import timber.log.Timber

/**
created by Mehmet E. Yıldız
 **/

@Composable
fun CryptoListScreen(
    navController: NavController,
    viewModel: CryptoListViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colors.secondary,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Text(
                "CryptoApp",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(10.dp))
            // search
            searchBar(
                hint = "Ara", modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onSearch = {
                   Log.e("TAG", "search : $it")
                }
            )

            Spacer(modifier = Modifier.height(10.dp))
            // list
        }

    }

}

@Composable
fun searchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    // by kullanarak text.value yazmıyoruz artık. by dersek, direkt değeri alabiliyoruz
    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = { newString ->
                text = newString
                onSearch(newString)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                }
        )

        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }


}