package com.sangeetha.leadertask.data.remote.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Company(
    val bs: String,
    val catchPhrase: String,
    @ColumnInfo(name = "company_name")
    @SerializedName("name")
    val name: String
): Parcelable