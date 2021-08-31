package com.chkan.testwork.model

import com.squareup.moshi.Json

data class MenuModel(
    @Json(name = "data") val menus: List<MenuProperty>
)

data class MenuProperty(
    @Json(name = "title") val title: String,
    @Json(name = "id")  val id: String,
    @Json(name = "categories") val categories: List<Categories>
)

data class Categories(
    @Json(name = "title") val title: String,
    @Json(name = "id")  val id: String,
    @Json(name = "subcategories") val subcategories: List<SubCategories>
)

data class SubCategories(
    @Json(name = "title") val title: String
)