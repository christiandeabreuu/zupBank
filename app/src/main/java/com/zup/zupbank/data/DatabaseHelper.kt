package com.zup.zupbank.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zup.zupbank.common.Constants
import com.zup.zupbank.data.local.dao.UserDao
import com.zup.zupbank.data.local.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper? {
            if (INSTANCE == null) {
                synchronized(DatabaseHelper::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        DatabaseHelper::class.java,
                        Constants.BASE_NAME_DATABASE,
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }
    }
}
