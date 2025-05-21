//package com.moviecatalog.di
//
//import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
//import com.moviecatalog.common.application.MovieCatalogApp
//import com.moviecatalog.data.data_sources.remote.common.interceptors.InternetConnectionInterceptor
//import com.moviecatalog.data.data_sources.remote.common.interceptors.LiveNetworkChecker
//import com.moviecatalog.data.data_sources.remote.common.interceptors.LiveNetworkCheckerImpl
//import com.moviecatalog.data.data_sources.remote.main_api.interceptors.TokenInterceptor
//import com.moviecatalog.data.data_sources.remote.main_api.interceptors.UnauthorizedResponseInterceptor
//import com.moviecatalog.data.data_sources.remote.main_api.interceptors.UnauthorizedResponseHandler
//import com.moviecatalog.data.data_sources.remote.main_api.interceptors.UnauthorizedResponseHandlerImpl
//import com.moviecatalog.data.data_sources.remote.main_api.services.AuthService
//import com.moviecatalog.data.data_sources.remote.main_api.services.FavoriteMoviesService
//import com.moviecatalog.data.data_sources.remote.main_api.services.MoviesService
//import com.moviecatalog.data.data_sources.remote.main_api.services.ReviewsService
//import com.moviecatalog.data.data_sources.remote.main_api.services.UserService
//import kotlinx.serialization.json.Json
//import okhttp3.OkHttpClient
//import okhttp3.MediaType.Companion.toMediaType
//import retrofit2.Retrofit
//
//object ApplicationModule {
//    private const val MAIN_API_BASE_URL = "https://react-midterm.kreosoft.space/"
//    private const val KINOPOISK_API_BASE_URL = "https://kinopoiskapiunofficial.tech/"
//
//    val liveNetworkChecker: LiveNetworkChecker = LiveNetworkCheckerImpl(MovieCatalogApp.getApp())
//    val unauthorizedResponseHandler: UnauthorizedResponseHandler = UnauthorizedResponseHandlerImpl()
////    val responseErrorHandler: ResponseErrorHandler = MovieCatalogResponseErrorHandler()
//
//    private val mainClient = OkHttpClient.Builder()
//        .retryOnConnectionFailure(true)
//        .addInterceptor(TokenInterceptor())
//        .addInterceptor(InternetConnectionInterceptor(liveNetworkChecker))
//        .addInterceptor(UnauthorizedResponseInterceptor(unauthorizedResponseHandler))
//        .build()
////    private val kinopoiskClient = OkHttpClient.Builder()
////        .retryOnConnectionFailure(true)
////        .addInterceptor(InternetConnectionInterceptor(liveNetworkChecker))
////        .addInterceptor(AuthorizationInterceptor())
////        .build()
//
//    private val mainRetrofit = Retrofit.Builder()
//        .client(mainClient)
//        .addConverterFactory(Json.asConverterFactory(getContentType()))
//        .baseUrl(MAIN_API_BASE_URL)
//        .build()
////    private val kinopoiskRetrofit = Retrofit.Builder()
////        .client(kinopoiskClient)
////        .addConverterFactory(Json.asConverterFactory(getContentType()))
////        .baseUrl(KINOPOISK_API_BASE_URL)
////        .build()
//
//    val authService: AuthService = mainRetrofit.create(AuthService::class.java)
//    val favoriteMoviesService: FavoriteMoviesService = mainRetrofit.create(FavoriteMoviesService::class.java)
//    val moviesService: MoviesService = mainRetrofit.create(MoviesService::class.java)
//    val reviewsService: ReviewsService = mainRetrofit.create(ReviewsService::class.java)
//    val userService: UserService = mainRetrofit.create(UserService::class.java)
////    val kinopoiskFilmsService: KinopoiskFilmsService = kinopoiskRetrofit.create(KinopoiskFilmsService::class.java)
////    val kinopoiskPersonsService: KinopoiskPersonsService = kinopoiskRetrofit.create(KinopoiskPersonsService::class.java)
//
//    private fun getContentType() =
//        "application/json".toMediaType()
//}