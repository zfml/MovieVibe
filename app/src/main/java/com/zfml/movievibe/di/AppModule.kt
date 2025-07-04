package com.zfml.movievibe.di

import android.app.Application
import androidx.room.Room
import com.zfml.movievibe.MovieApp
import com.zfml.movievibe.data.local.database.MovieDatabase
import com.zfml.movievibe.data.remote.api.MovieApi
import com.zfml.movievibe.data.repository.MovieRepositoryImpl
import com.zfml.movievibe.domain.repository.MovieRepository
import com.zfml.movievibe.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(
        app: Application,
    ): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            "movie.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(db: MovieDatabase) = db.movieDao()


    @Provides
    @Singleton
    fun provideMovieRepository(
        db: MovieDatabase,
        api: MovieApi
    ): MovieRepository {
        return MovieRepositoryImpl(db = db,api = api)
    }


}