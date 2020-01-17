package com.elimak.krikey.api

import com.elimak.krikey.R
import com.elimak.krikey.db.vo.Category
import com.elimak.krikey.db.vo.ResultPic
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiServices {

    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("images/search?")
    suspend fun getImagesByCategory(@Query("category_ids") catId: Int,
                                    @Query("limit") limit: Int,
                                    @Query("page") page: Int,
                                    @Query("order") order: String): List<ResultPic>

    companion object Factory {
        const val BASE_URL = "https://api.thecatapi.com/v1/"

        private val httpClient = OkHttpClient.Builder()

        fun create(): IApiServices {
            val retrofit = retrofit2.Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient.addInterceptor{chain->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .header("x-api-key", R.string.cat_api_key.toString())
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }.build())
                .build()

            return retrofit.create(IApiServices::class.java)
        }
    }
}