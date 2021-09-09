package com.sangeetha.leadertask.data.remote

import com.sangeetha.leadertask.data.remote.model.Contacts
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getContacts(): Response<Contacts>
}