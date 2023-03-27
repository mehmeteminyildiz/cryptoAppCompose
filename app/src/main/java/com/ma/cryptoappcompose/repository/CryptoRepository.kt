package com.ma.cryptoappcompose.repository

import com.ma.cryptoappcompose.data.CryptoDetail
import com.ma.cryptoappcompose.data.CryptoList
import com.ma.cryptoappcompose.service.CryptoAPI
import com.ma.cryptoappcompose.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
created by Mehmet E. Yıldız
 **/

@ActivityScoped
class CryptoRepository
@Inject constructor(
    private val api: CryptoAPI
) {

    suspend fun getCryptoList(): Resource<CryptoList> {
        val response = try {
            api.getCryptoList()
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.Error("error broo")
        }

        return Resource.Success(response)
    }

    suspend fun getCryptoDetail(id:Int): Resource<CryptoDetail> {
        val response = try {
            api.getCrypto(id)
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.Error("error broo")
        }
        return Resource.Success(response)
    }


}