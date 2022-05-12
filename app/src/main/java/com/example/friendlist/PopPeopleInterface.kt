package com.example.friendlist

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PopPeopleInterface {
    @GET("/3/person/popular")
    fun getPeople(@Query("api_key") key: String): Call<PopPeople>
}