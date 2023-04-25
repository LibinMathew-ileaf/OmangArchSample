package com.example.omangarchsample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.omangarchsample.database.dao.UserDao
import com.example.omangarchsample.model.database.User

@Database(
    entities = [
        User::class
    ],
    version = 1,

    exportSchema = true
)
abstract class OmangDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserDao

    companion object {
        @Volatile
        private var instance: OmangDatabase? = null

        fun getDatabase(context: Context): OmangDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, OmangDatabase::class.java, "omang")
                .build()
    }


}
