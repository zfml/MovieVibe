package com.zfml.movievibe.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zfml.movievibe.data.local.dao.MovieDao
import com.zfml.movievibe.data.local.model.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}