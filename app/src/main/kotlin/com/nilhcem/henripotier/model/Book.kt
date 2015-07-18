package com.nilhcem.henripotier.model

import java.io.Serializable

public data class Book(val isbn: String, val title: String, val price: Float, val cover: String) : Serializable
