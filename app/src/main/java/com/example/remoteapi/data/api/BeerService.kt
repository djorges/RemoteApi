package com.example.remoteapi.data.api

import com.example.remoteapi.data.dto.BeerDto
import okhttp3.RequestBody
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface BeerService {
    @GET("beers")
    suspend fun getBeers(
        @Query("page") page:Int,
        @Query("per_page") pageCount:Int
    ):List<BeerDto>

    /*TODO: Examples: Users API */
    /*@Headers(
        "Cache-Control: max-age=640000",
    )
    @GET("users")
    suspend fun getAllUsers(): List<BeerDto>

    @FormUrlEncoded
    @POST("users/edit")
    suspend fun updateUserData(
        @Field("first_name") first: String,
        @Field("last_name") last_name: String
    ): BeerDto

    @Multipart
    @PUT("user/photo")
    suspend fun updateUserProfile(
        @Path("photo") photo: RequestBody,
        @Path("description") description: RequestBody
    )

    @DELETE("user/{id}")
    suspend fun deleteUser(@Path("id") id:Int): RequestBody

     */
}