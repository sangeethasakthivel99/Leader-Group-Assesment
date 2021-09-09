package com.sangeetha.leadertask.data.remote.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
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
): Parcelable