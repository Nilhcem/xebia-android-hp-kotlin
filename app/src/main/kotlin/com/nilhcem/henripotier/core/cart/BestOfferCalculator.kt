package com.nilhcem.henripotier.core.cart

import com.nilhcem.henripotier.model.CommercialOffer
import com.nilhcem.henripotier.model.Type

object BestOfferCalculator {

    fun getBestOffer(amount: Float, offers: List<CommercialOffer>): CommercialOffer? {
        val map0 = offers.toMap { computeDiscount(amount, it) }
        val map = map0.toSortedMap(compareBy<Float> { it })
        return if (map.isEmpty()) null else map[map.lastKey()]
    }

    fun computeDiscount(amount: Float, offer: CommercialOffer): Float =
            when (offer.type) {
                Type.MINUS -> offer.value
                Type.PERCENTAGE -> amount * offer.value / 100
                Type.SLICE -> (amount / offer.sliceValue).toInt() * offer.value
                else -> 0F
            }
}
