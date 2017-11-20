package com.nilhcem.henripotier.network.api

import com.nilhcem.henripotier.model.Book
import com.nilhcem.henripotier.model.CommercialOffers
import retrofit.http.GET
import retrofit.http.Path

interface BookStoreApi {

    @GET("/books")
    fun getBooks(): List<Book>

    @GET("/books/{isbn}/commercialOffers")
    fun getCommercialOffers(@Path("isbn") isbn: String): CommercialOffers
}
