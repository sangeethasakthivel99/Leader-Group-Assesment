package com.sangeetha.leadertask.data.remote.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Company(
    val bs: String,
    val catchPhrase: String,
    @ColumnInfo(name = "company_name")
    @SerializedName("name")
    val name: String
)