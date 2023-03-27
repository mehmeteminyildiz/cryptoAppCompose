package com.ma.cryptoappcompose.viewmodel

import androidx.lifecycle.ViewModel
import com.ma.cryptoappcompose.data.CryptoDetail
import com.ma.cryptoappcompose.repository.CryptoRepository
import com.ma.cryptoappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
created by Mehmet E. Yıldız
 **/
@HiltViewModel
class CryptoDetailViewModel
@Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    // Yöntem 2: suspend kullanmak
    suspend fun getDetail(id: String): Resource<CryptoDetail> {
        return repository.getCryptoDetail(id)
    }
}