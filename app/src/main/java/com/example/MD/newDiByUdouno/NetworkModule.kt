//package com.moviecatalog.di
//
//import com.moviecatalog.data.repositories_impl.AuthRepositoryImpl
//import com.moviecatalog.data.repositories_impl.FavoriteMoviesRepositoryImpl
//import com.moviecatalog.data.repositories_impl.MoviesRepositoryImpl
//import com.moviecatalog.data.repositories_impl.ReviewsRepositoryImpl
//import com.moviecatalog.data.repositories_impl.UserProfileRepositoryImpl
//import com.moviecatalog.domain.repositories.AuthRepository
//import com.moviecatalog.domain.repositories.FavoriteMoviesRepository
//import com.moviecatalog.domain.repositories.MoviesRepository
//import com.moviecatalog.domain.repositories.ReviewsRepository
//import com.moviecatalog.domain.repositories.UserProfileRepository

//object NetworkModule {
//    val authRepository: AuthRepository =
//        AuthRepositoryImpl(ApplicationModule.authService)
//    val favoriteMoviesRepository: FavoriteMoviesRepository =
//        FavoriteMoviesRepositoryImpl(ApplicationModule.favoriteMoviesService)
//    val moviesRepository: MoviesRepository = MoviesRepositoryImpl(
//        ApplicationModule.moviesService,
//        ApplicationModule.kinopoiskFilmsService,
//        ApplicationModule.kinopoiskPersonsService)
//    val reviewsRepository: ReviewsRepository =
//        ReviewsRepositoryImpl(ApplicationModule.reviewsService)
//    val userProfileRepository: UserProfileRepository =
//        UserProfileRepositoryImpl(ApplicationModule.userService)
//}