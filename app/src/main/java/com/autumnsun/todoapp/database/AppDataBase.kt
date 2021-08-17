package com.autumnsun.todoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.autumnsun.todoapp.database.dao.ItemEntityDao
import com.autumnsun.todoapp.database.entity.ItemEntity

@Database(
    entities = [ItemEntity::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        private var appDataBase: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
            if (appDataBase != null) {
                return appDataBase!!
            }
            appDataBase = Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "to-buy-database"
            ).build()
            return appDataBase!!
        }

    }
    abstract fun itemEntityDao(): ItemEntityDao
}