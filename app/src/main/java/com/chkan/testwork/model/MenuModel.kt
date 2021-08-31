package com.chkan.testwork.model

import com.squareup.moshi.Json

data class MenuModel(
    @Json(name = "data") val menus: List<MenuProperty>
)

data class MenuProperty(
    @Json(name = "title") val title: String,
    @Json(name = "id")  val id: String
)