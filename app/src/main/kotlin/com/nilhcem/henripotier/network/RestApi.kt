package com.nilhcem.henripotier.network

import com.nilhcem.henripotier.BuildConfig
import com.nilhcem.henripotier.network.api.BookStoreApi
import retrofit.RestAdapter

public object RestApi {

    public val bookStoreApi: BookStoreApi

    init {
        val restAdapter = RestAdapter.Builder().setEndpoint(BuildConfig.WS_ENDPOINT).build()
        bookStoreApi = restAdapter.create(javaClass<BookStoreApi>())
    }
}
