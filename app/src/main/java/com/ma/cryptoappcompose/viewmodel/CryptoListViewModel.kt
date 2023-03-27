package com.ma.cryptoappcompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ma.cryptoappcompose.data.CryptoListItem
import com.ma.cryptoappcompose.repository.CryptoRepository
import com.ma.cryptoappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
created by Mehmet E. Yıldız
 **/
@HiltViewModel
class CryptoListViewModel
@Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    // liveData kullanmak yerine mutableState kullanabiliriz
    // state'lerde değişiklik olursa recomposition'u çağırabilmek amacımız
    var cryptoList = mutableStateOf<List<CryptoListItem>>(listOf())
    var errorMessage = mutableStateOf<String>("")
    var isLoading = mutableStateOf<Boolean>(false)


    private var initialCryptoList = listOf<CryptoListItem>()
    private var isSearchStarting = true


    init {
        loadCryptos()
    }

    fun searchCryptoList(query: String) {
        val listToSearch = if (isSearchStarting) {
            cryptoList.value
        } else {
            initialCryptoList
        }

        // aramayı coroutine içinde yapacağız
        viewModelScope.launch {
            if (query.isEmpty()) {
                cryptoList.value = initialCryptoList
                isSearchStarting = true
                return@launch
            } else {
                val results = listToSearch.filter {
                    it.currency.contains(query.trim(), ignoreCase = true)
                }

                if (isSearchStarting) {
                    initialCryptoList = cryptoList.value
                    isSearchStarting = false
                }
                cryptoList.value = results
            }

        }

    }


    // scope veya suspend yapmadan kabul etmiyor. Burada ikisinin de avantaj ve dezavantajları bulunmakta.
    // Yöntem 1: viewModelScope:
    //
    fun loadCryptos() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getCryptoList()
            when (result) {
                is Resource.Success -> {
                    val cryptoItems = result.data!!.mapIndexed { index, cryptoListItem ->
                        CryptoListItem(cryptoListItem.currency, cryptoListItem.price)
                    } as List<CryptoListItem>

                    errorMessage.value = ""
                    isLoading.value = false
                    cryptoList.value += cryptoItems
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
                is Resource.Loading -> {
                    isLoading.value = true
                }
            }
        }
    }
}