package com.example.movieapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favoriteDao() : FavoriteDao

    companion object {

        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        fun getDatabase(context: Context): FavoriteDatabase? {
            if (INSTANCE == null) {
                synchronized(FavoriteDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            FavoriteDatabase::class.java, "favorite_database"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}