package com.sangeetha.leadertask.data.remote.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactsItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @Embedded
    val address: Address,
    @Embedded
    val company: Company,
    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)