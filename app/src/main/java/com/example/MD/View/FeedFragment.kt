package com.example.MD.View

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.MD.Domain.AddFavGenreUseCase
import com.example.MD.Domain.AddFavoritesUseCase
import com.example.MD.Domain.GenreModel
import com.example.MD.Domain.GetFavGenresUseCase
import com.example.MD.Domain.Movie
import com.example.MD.Domain.MoviesPageUseCase
import com.example.MD.R
import com.example.MD.ViewModel.FeedScreenFactory
import com.example.MD.ViewModel.FeedViewModel
import com.example.MD.data.entity.DB.GenreForDBModelDB
import com.example.MD.data.mapper.GenreDTOMapper
import com.example.MD.data.mapper.GenreListDBModelMapper
import com.example.MD.data.mapper.GenreModelDBMapper
import com.example.MD.data.mapper.MovieDetailsModelDTOMapper
import com.example.MD.data.mapper.MovieElementModelDTOMapper
import com.example.MD.data.mapper.MoviesListModelDTOMapper
import com.example.MD.data.mapper.MoviesPagedListModelDTOMapper
import com.example.MD.data.mapper.PageListModelDTOMapper
import com.example.MD.data.mapper.ReviewModelDTOMapper
import com.example.MD.data.mapper.ReviewShortModelDTOMapper
import com.example.MD.data.mapper.UserShortModelDTOMapper
import com.example.MD.data.repository.FavouriteMoviesRepositoryImpl
import com.example.MD.data.repository.LocalDataSourceImpl
import com.example.MD.data.repository.MovieRepositoryImpl
import com.example.MD.databinding.FeedScreenFragmentBinding
import kotlinx.coroutines.launch

class FeedFragment : Fragment(R.layout.feed_screen_fragment) {
    private var binding: FeedScreenFragmentBinding? = null
    private val genreModelDBMapper = GenreModelDBMapper()
    private val genreListDBModelMapper = GenreListDBModelMapper(genreModelDBMapper)
    private val genreMapper = GenreDTOMapper()
    private val reviewShortModel = ReviewShortModelDTOMapper()
    private val shortModelMapper = UserShortModelDTOMapper()
    private val mapper = MovieElementModelDTOMapper(genreMapper, reviewShortModel)
    private val moviesListMapper = MoviesListModelDTOMapper(mapper)
    private val mapperPage = PageListModelDTOMapper()
    private val reviewMapper = ReviewModelDTOMapper(shortModelMapper)
    private val moviesDtoMapper = MovieDetailsModelDTOMapper(genreMapper, reviewMapper)
    private val moviesPagedDTOMapper = MoviesPagedListModelDTOMapper(mapper, mapperPage)
    private val movieRepository: Movie = MovieRepositoryImpl(moviesDtoMapper, moviesPagedDTOMapper)
    private val favRepository = FavouriteMoviesRepositoryImpl(moviesListMapper)
    private val localRepository = LocalDataSourceImpl(genreListDBModelMapper, genreModelDBMapper)
    private val movieUseCase = MoviesPageUseCase(movieRepository)
    private val addFavUseCase = AddFavoritesUseCase(favRepository)
    private val getFavGenreUseCase = GetFavGenresUseCase(localRepository)
    private val addFavGenreUseCase = AddFavGenreUseCase(localRepository)
    private lateinit var viewModel: FeedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FeedScreenFragmentBinding.bind(view)
        binding!!.bottomCard.isClickable = false
        viewModel = ViewModelProvider(
            this,
            FeedScreenFactory(addFavUseCase, movieUseCase, getFavGenreUseCase, addFavGenreUseCase)
        )[FeedViewModel::class.java]
        observeData(viewModel)
        binding!!.movieBtn.setOnClickListener {
            findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToMovieFragment())
        }
        binding!!.profileBtn.setOnClickListener {
            findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToProfileFrament())
        }
        lifecycleScope.launch { requestMovie() }
        binding!!.motionLayout.setTransitionListener(object : TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                when (currentId) {
                    R.id.offScreenPass -> {
                        motionLayout!!.progress = 0f
                        motionLayout.setTransition(R.id.rest, R.id.pass)
                        viewModel.onMovieSwap()
                    }

                    R.id.offScreenLike -> {
                        motionLayout!!.progress = 0f
                        motionLayout.setTransition(R.id.rest, R.id.like)
                        viewModel.onMovieSwap()
                        viewModel.addFavor(viewModel.movie.value!!.id)
                    }
                }
            }
        })
    }

    private fun getMoviesPage(imageView: ImageView, uri: String?) {
        Glide.with(imageView.context).load(Uri.parse(uri)).into(imageView)
    }

    private fun setFilmChar(name: String?, country: String?, year: Int) {
        binding!!.name.text = name
        binding!!.countryYear.text = country + " · " + year.toString()
    }

    private fun setFilmGenre(genres: List<GenreModel>) {
        if (genres.isNotEmpty()) {
            val genresIdFirst = genres[0].id
            val genresNameFirst = genres[0].name.toString()
            binding!!.chip1.setOnClickListener {
                viewModel.addFavGenre(GenreModel(genresIdFirst,genresNameFirst))
            }
            binding!!.chip1.visibility = VISIBLE
            binding!!.chip1.text = genres[0].name.toString()
        } else {
            binding!!.chip1.visibility = GONE
        }
        if (genres.size >= 2) {
            val genresIdSecond = genres[1].id
            val genresNameSecond = genres[1].name.toString()
//            binding!!.chip2.setOnClickListener {
//                viewModel.addFavGenre(GenreModel(genresIdSecond,genresNameSecond))
//            }
            binding!!.chip2.visibility = VISIBLE
            binding!!.chip2.text = genres[1].name.toString()
        } else {
            binding!!.chip2.visibility = GONE
        }
        if (genres.size >= 3) {
            val genresIdThird = genres[2].id
            val genresNameThird = genres[2].name.toString()
            binding!!.chip3.setOnClickListener {
                viewModel.addFavGenre(GenreModel(genresIdThird,genresNameThird))
            }
            binding!!.chip3.visibility = VISIBLE
            binding!!.chip3.text = genres[2].name.toString()
        } else {
            binding!!.chip3.visibility = GONE
        }
    }

    private fun observeData(viewModel: FeedViewModel) {
        lifecycleScope.launch {
            viewModel.movie.collect { movie ->
                movie?.let {
                    getMoviesPage(binding!!.topCard, it.poster)
                    setFilmChar(it.name, it.country, it.year)
                    it.genres?.let { setFilmGenre(it) }
                }
            }

        }
        lifecycleScope.launch {
            viewModel.movieBack.collect { movieBack ->
                movieBack?.let {
                    getMoviesPage(binding!!.bottomCard, it.poster)
                }
            }
        }
    }

    private fun requestMovie() {
        viewModel.onMovieSwap()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}