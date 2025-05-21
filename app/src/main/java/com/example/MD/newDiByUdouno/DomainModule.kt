//package com.moviecatalog.di
//
//import com.example.MD.Domain.AddFavoritesUseCase
//import com.example.MD.Domain.AddReviewUseCase
//import com.example.MD.Domain.DeleteFavoritesUseCase
//import com.example.MD.Domain.DeleteReviewUseCase
//import com.example.MD.Domain.DetailsUseCase
//import com.example.MD.Domain.EditProfileUseCase
//import com.example.MD.Domain.EditReviewUseCase
//import com.example.MD.Domain.GetFavoritesUseCase
//import com.example.MD.Domain.GetProfileUseCase
//import com.example.MD.Domain.LoginUseCase
//import com.example.MD.Domain.LogoutUseCase
//import com.example.MD.Domain.RegisterUseCase
//
//object DomainModule {
//    val loginUseCase = LoginUseCase(NetworkModule.authRepository)
//    val logoutUseCase = LogoutUseCase(NetworkModule.authRepository)
//    val registerUseCase = RegisterUseCase(NetworkModule.authRepository)
//
////    val addToFriendsUseCase = AddToFriendsUseCase(DatabaseModule.friendsRepository)
////    val deleteFromFriendsUseCase = DeleteFromFriendsUseCase(DatabaseModule.friendsRepository)
////    val editFriendsAvatarUseCase = EditFriendsAvatarUseCase(DatabaseModule.friendsRepository)
////    val getAllFriendsUseCase = GetAllFriendsUseCase(DatabaseModule.friendsRepository)
////    val isInFriendsUseCase = IsInFriendsUseCase(DatabaseModule.friendsRepository)
//
////    val addToFavoriteGenresUseCase = AddToFavoriteGenresUseCase(DatabaseModule.favoriteGenresRepository)
////    val deleteFromFavoriteGenresUseCase = DeleteFromFavoriteGenresUseCase(DatabaseModule.favoriteGenresRepository)
////    val getAllFavoriteGenresUseCase = GetAllFavoriteGenresUseCase(DatabaseModule.favoriteGenresRepository)
////    val isInFavoriteGenresUseCase = IsInFavoriteGenresUseCase(DatabaseModule.favoriteGenresRepository)
//
//    val getMovieAdditionalDetailsUseCase = DetailsUseCase(NetworkModule.moviesRepository)
////    val getNRandomNotIgnoredMoviesUseCase = GetNRandomNotIgnoredMoviesUseCase(getRandomNotIgnoredMovieUseCase)
////    val getRandomNotIgnoredMovieDetailsUseCase = GetRandomNotIgnoredMovieDetailsUseCase(
////            NetworkModule.moviesRepository,
////            getRandomNotIgnoredMovieUseCase)
//
//    val addToFavoriteMoviesUseCase = AddFavoritesUseCase(NetworkModule.favoriteMoviesRepository)
//    val deleteFromFavoriteMoviesUseCase = DeleteFavoritesUseCase(NetworkModule.favoriteMoviesRepository)
//    val getAllFavoriteMoviesUseCase = GetFavoritesUseCase(NetworkModule.favoriteMoviesRepository)
////    val isMovieInFavoritesUseCase = IsMovieInFavoritesUseCase(NetworkModule.favoriteMoviesRepository)
//
////    val addToIgnoredMovieUseCase = AddToIgnoredMoviesUseCase(DatabaseModule.ignoredMoviesRepository)
////    val deleteMovieFromIgnoredUseCase = DeleteMovieFromIgnoredUseCase(DatabaseModule.ignoredMoviesRepository)
////    val isMovieInIgnoredUseCase = IsMovieInIgnoredUseCase(DatabaseModule.ignoredMoviesRepository)
//
//    val editProfileUseCase = EditProfileUseCase(NetworkModule.userProfileRepository)
////    val getProfileIdUseCase = GetProfileIdUseCase(NetworkModule.userProfileRepository)
//    val getProfileUseCase = GetProfileUseCase(NetworkModule.userProfileRepository)
//
//    val addReviewUseCase = AddReviewUseCase(NetworkModule.reviewsRepository)
//    val deleteReviewUseCase = DeleteReviewUseCase(NetworkModule.reviewsRepository)
//    val editReviewUseCase = EditReviewUseCase(NetworkModule.reviewsRepository)
//}