package com.nilhcem.henripotier.network.api

import com.nilhcem.henripotier.model.Book
import retrofit.http.GET

interface BookStoreApi {

    GET("/books")
    fun getBooks(): List<Book>
}
