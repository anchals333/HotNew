package com.anchal.hotnew.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anchal.hotnew.home.hottab.HotDao
import com.anchal.hotnew.home.hottab.HotModel
import com.anchal.hotnew.home.newtab.NewDao
import com.anchal.hotnew.home.newtab.NewModel


@Database(entities = [HotModel::class, NewModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hotDao(): HotDao
    abstract fun newDao(): NewDao
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "hotnew_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
