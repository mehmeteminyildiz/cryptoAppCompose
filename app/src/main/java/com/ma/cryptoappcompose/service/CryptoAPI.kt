package com.ma.cryptoappcompose.service

import com.ma.cryptoappcompose.data.CryptoDetail
import com.ma.cryptoappcompose.data.CryptoList
import com.ma.cryptoappcompose.util.Constants
import retrofit2.http.GET

/**
created by Mehmet E. Yıldız
 **/
interface CryptoAPI {

    @GET(Constants.LIST_END_POINT)
    suspend fun getCryptoList(): CryptoList


    @GET(Constants.DETAIL_END_POINT)
    suspend fun getCrypto(): CryptoDetail


}