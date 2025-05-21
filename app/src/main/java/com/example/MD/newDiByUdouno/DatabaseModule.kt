//package com.moviecatalog.di
//
//import androidx.room.Room
//import com.moviecatalog.common.application.MovieCatalogApp
//import com.moviecatalog.data.data_sources.local.AppDatabase
//import com.moviecatalog.data.repositories_impl.FavoriteGenresRepositoryImpl
//import com.moviecatalog.data.repositories_impl.FriendsRepositoryImpl
//import com.moviecatalog.data.repositories_impl.IgnoredMoviesRepositoryImpl
//import com.moviecatalog.domain.repositories.FavoriteGenresRepository
//import com.moviecatalog.domain.repositories.FriendsRepository
//import com.moviecatalog.domain.repositories.IgnoredMoviesRepository
//
//object DatabaseModule{
//    private val database = Room.databaseBuilder(
//            MovieCatalogApp.getApp(),
//            AppDatabase::class.java,
//            AppDatabase.DATABASE_NAME
//        ).build()
//
//    val favoriteGenresRepository: FavoriteGenresRepository =
//        FavoriteGenresRepositoryImpl(database.genreDao())
//    val friendsRepository: FriendsRepository =
//        FriendsRepositoryImpl(database.userDao())
//    val ignoredMoviesRepository: IgnoredMoviesRepository =
//        IgnoredMoviesRepositoryImpl(database.ignoredMoviesIdsDao())
//}