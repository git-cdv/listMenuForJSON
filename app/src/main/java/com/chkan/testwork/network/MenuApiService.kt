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
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getPhotos] method
 */
interface MenuApiService {

    @GET("categories?action=list")
    suspend fun getMenu() : MenuModel
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MenuApi {
    val retrofitService: MenuApiService by lazy { retrofit.create(MenuApiService::class.java) }
}


