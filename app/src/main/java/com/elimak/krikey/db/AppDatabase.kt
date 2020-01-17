package com.elimak.krikey.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elimak.krikey.db.vo.Category
import com.elimak.krikey.db.vo.CategoryDao
import com.elimak.krikey.db.vo.FavoriteDao
import com.elimak.krikey.db.vo.ResultPic

@Database(entities = [Category::class, ResultPic::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao() : CategoryDao
    abstract fun favoriteDao() : FavoriteDao

    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) { INSTANCE ?: buildDatabase(context).also { INSTANCE = it } }

        private fun buildDatabase(ctx: Context) =
            Room.databaseBuilder(ctx.applicationContext, AppDatabase::class.java, "krikey_database")
                .fallbackToDestructiveMigration()
                .build()
    }
}
