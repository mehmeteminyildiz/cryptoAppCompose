package com.ma.cryptoappcompose.data

import com.google.gson.annotations.SerializedName

data class CryptoDetailItem(
    @SerializedName("id") val id: String? = null,
    @SerializedName("logo_url") val logo_url: String? = null,
    @SerializedName("name") val name: String? = null
)