package com.sangeetha.leadertask.repository

import com.sangeetha.leadertask.data.local.LeaderGroupDao
import com.sangeetha.leadertask.data.remote.ApiService
import com.sangeetha.leadertask.data.remote.model.Contacts
import com.sangeetha.leadertask.data.remote.model.ContactsItem
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Response
import javax.inject.Inject

class ContactsRepository @Inject constructor(
    private val apiService: ApiService,
    private val leaderGroupDao: LeaderGroupDao
) {
    suspend fun getContacts(): Response<Contacts> {
        val response: Response<Contacts> = apiService.getContacts()
        if (response.isSuccessful){
            response.body()?.forEach {
                insertDataToDB(it)
            }
        }
        return response
    }

    private suspend fun insertDataToDB(contacts: ContactsItem) = leaderGroupDao.insertContact(contacts)

    fun getSavedContacts() = leaderGroupDao.getContacts()
}