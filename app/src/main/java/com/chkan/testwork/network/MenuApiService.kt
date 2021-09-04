package com.chkan.testwork.network


import com.chkan.testwork.model.MenuModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://burger.devforfun.net/api.php/v1/"

/**
 * Создаем фабрику адаптеров для конвертаций в Ретрофите
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Создаем обьект Ретрофита с конвертером Moshi
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * Открытый интерфейс, который предоставляет гет-метод (BASE_URL+GET value)
 */
interface MenuApiService {

    @GET("categories?action=list")
    suspend fun getMenu() : Response<MenuModel>
}

/**
 * Общедоступный объект Api, предоставляющий ленивую инициализацию службы
 */
object MenuApi {
    val retrofitService: MenuApiService by lazy { retrofit.create(MenuApiService::class.java) }
}


