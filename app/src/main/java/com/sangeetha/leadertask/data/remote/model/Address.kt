package com.sangeetha.leadertask.data.remote.model

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    val city: String,
    @Embedded
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
): Parcelable
