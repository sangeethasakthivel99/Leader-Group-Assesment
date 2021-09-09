package com.sangeetha.leadertask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sangeetha.leadertask.data.remote.model.ContactsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface LeaderGroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contactsItem: ContactsItem)

    @Query("SELECT * FROM contactsitem")
    fun getContacts(): Flow<List<ContactsItem>>
}
