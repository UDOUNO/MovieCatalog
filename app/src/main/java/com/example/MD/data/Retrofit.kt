package com.example.MD.data

import android.util.Log
import com.example.MD.SingUpActivity
import com.example.MD.data.datasource.remote.AuthApiService
import com.example.MD.data.datasource.remote.FavoriteMoviesApiService
import com.example.MD.data.datasource.remote.MovieApiService
import com.example.MD.data.datasource.remote.ReviewApiService
import com.example.MD.data.datasource.remote.UserApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object Retrofit {
    private const val BASE_URL = "https://react-midterm.kreosoft.space/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).client(clientBuilder.build()).build()
    }
    private val movieClient = OkHttpClient()
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val clientBuilder: OkHttpClient.Builder =
        movieClient.newBuilder().addInterceptor(interceptor).addInterceptor(Interceptor { chain ->
            val original: Request = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                .header("Authorization","Bearer " + SingUpActivity.getApp().appSharedPref.getString("token",""))
            SingUpActivity.getApp().appSharedPref.getString("token","")?.let { Log.e("", it) }
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        })
    val Auth: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)
    }
    val FavouriteMovies: FavoriteMoviesApiService by lazy {
        retrofit.create(FavoriteMoviesApiService::class.java)
    }
    val Movie: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
    val Reviews: ReviewApiService by lazy {
        retrofit.create(ReviewApiService::class.java)
    }
    val User: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
}
