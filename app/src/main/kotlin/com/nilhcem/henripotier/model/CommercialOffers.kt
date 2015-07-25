package com.nilhcem.henripotier.model

import com.google.gson.annotations.SerializedName

data class CommercialOffers(val offers: List<CommercialOffer>)

data class CommercialOffer(val type: Type, val value: Float, val sliceValue: Float)

enum class Type() {
    @SerializedName("percentage") PERCENTAGE,
    @SerializedName("minus") MINUS,
    @SerializedName("slice") SLICE
}
