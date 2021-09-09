package com.sangeetha.leadertask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sangeetha.leadertask.data.remote.model.ContactsItem

@Database(version = 1, entities = [
    ContactsItem::class
])
abstract class LeaderGroupDatabase: RoomDatabase() {
    abstract fun leaderGroupDao(): LeaderGroupDao
}