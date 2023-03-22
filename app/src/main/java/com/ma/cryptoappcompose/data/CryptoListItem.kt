package com.ma.cryptoappcompose.data

import com.google.gson.annotations.SerializedName

data class CryptoListItem(

    @SerializedName("currency") val currency: String? = null,
    @SerializedName("price") val price: String? = null
)