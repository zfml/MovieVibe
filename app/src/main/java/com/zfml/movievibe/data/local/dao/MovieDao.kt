package com.zfml.movievibe.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zfml.movievibe.data.local.model.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies WHERE category =:category ORDER BY releaseDate DESC")
    fun getMoviesByCategory(category: String): PagingSource<Int,MovieEntity>

    @Query("DELETE FROM movies WHERE category=:category")
    suspend fun clearCategory(category: String)

}