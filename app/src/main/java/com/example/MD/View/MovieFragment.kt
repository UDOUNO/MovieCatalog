package com.example.MD.View

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.MD.Domain.GetFavoritesUseCase
import com.example.MD.Domain.Movie
import com.example.MD.Domain.MovieElementModel
import com.example.MD.Domain.MoviesPageUseCase
import com.example.MD.R
import com.example.MD.ViewModel.MovieScreenViewModelFactory
import com.example.MD.ViewModel.MovieViewModel
import com.example.MD.addons.FilmItem
import com.example.MD.addons.FilmItemAdapter
import com.example.MD.addons.PosterItem
import com.example.MD.addons.PosterItemAdapter
import com.example.MD.addons.ViewPagerAdapter
import com.example.MD.data.mapper.GenreDTOMapper
import com.example.MD.data.mapper.MovieDetailsModelDTOMapper
import com.example.MD.data.mapper.MovieElementModelDTOMapper
import com.example.MD.data.mapper.MoviesListModelDTOMapper
import com.example.MD.data.mapper.MoviesPagedListModelDTOMapper
import com.example.MD.data.mapper.PageListModelDTOMapper
import com.example.MD.data.mapper.ReviewModelDTOMapper
import com.example.MD.data.mapper.ReviewShortModelDTOMapper
import com.example.MD.data.mapper.UserShortModelDTOMapper
import com.example.MD.data.repository.FavouriteMoviesRepositoryImpl
import com.example.MD.data.repository.MovieRepositoryImpl
import com.example.MD.databinding.MovieScreenLayoutBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MovieFragment : Fragment(R.layout.movie_screen_layout) {
    private var binding: MovieScreenLayoutBinding? = null
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
    private val movieUseCase = MoviesPageUseCase(movieRepository)
    private val getFavUseCase = GetFavoritesUseCase(favRepository)
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapterPage: ViewPagerAdapter
    private lateinit var adapterGrid: FilmItemAdapter
    private lateinit var adapterFav: PosterItemAdapter
    private var filmItems = ArrayList<FilmItem>()
    private var posterItems = ArrayList<PosterItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MovieScreenLayoutBinding.bind(view)
        viewModel = ViewModelProvider(
            this, MovieScreenViewModelFactory(getFavUseCase, movieUseCase)
        )[MovieViewModel::class.java]
        observeData(viewModel)
        lifecycleScope.launch { viewModel.getMovies() }
        lifecycleScope.launch { viewModel.getFavors() }
        binding!!.feedBtn.setOnClickListener {
            findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToFeedFragment())
        }
        binding!!.profileBtn.setOnClickListener {
            findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToProfileFrament())
        }
    }

    private suspend fun showCycleFilms(cycleFilmes: ArrayList<MovieElementModel>) {
        initAdapterPager(cycleFilmes)
        showScroll()
    }

    private suspend fun showScroll() {
        var progressBar = binding!!.progressBar
        progressBar.max = 1000
        progressBar.progress = 0
        while (true) {
            if(progressBar.progress == 0){
                binding!!.viewPager2.setCurrentItem(0)
            }
            if (progressBar.progress == 200) {
                binding!!.viewPager2.setCurrentItem(1)
            }
            if (progressBar.progress == 400) {
                binding!!.viewPager2.setCurrentItem(2)
            }
            if (progressBar.progress == 600) {
                binding!!.viewPager2.setCurrentItem(3)
            }
            if (progressBar.progress == 800) {
                binding!!.viewPager2.setCurrentItem(4)
            }
            if (progressBar.progress == 1000) {
                progressBar.progress = 0
                binding!!.viewPager2.setCurrentItem(0)
            }
            if (progressBar.progress / 200 != binding!!.viewPager2.currentItem) {
                progressBar.progress += (binding!!.viewPager2.currentItem) * 200 - progressBar.progress
            }
            progressBar.progress++
            delay(24)
        }
    }

    private fun initAdapterPager(cycleItems: ArrayList<MovieElementModel>) {
        adapterPage = ViewPagerAdapter(cycleItems, this)
        println(cycleItems)
        binding!!.viewPager2.adapter = adapterPage
    }

    private fun initAdapterPosters(posterItems: ArrayList<PosterItem>) {
        binding!!.favView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapterFav = PosterItemAdapter(posterItems)
        binding!!.favView.adapter = adapterFav
    }

    private fun initAdapterFilms(filmItems: ArrayList<FilmItem>) {
        binding!!.gridView.layoutManager = GridLayoutManager(context, 3)
        adapterGrid = FilmItemAdapter(filmItems)
        binding!!.gridView.adapter = adapterGrid
    }

    private fun observeData(viewModel: MovieViewModel) {
        lifecycleScope.launch {
            viewModel.movies.collect { movie ->
                movie?.let {
                    for (i in 0..<viewModel.movies.value!!.size) {
                        var temp = 0.0f
                        for (z in 0..<viewModel.movies.value!![i].reviews!!.size) {
                            temp += viewModel.movies.value!![i].reviews!![z].rating
                        }
                        temp /= viewModel.movies.value!![i].reviews!!.size
                        filmItems.add(
                            FilmItem(
                                iD = viewModel.movies.value!![i].id,
                                rate = (temp * 10.0f).roundToInt() / 10.0f,
                                isLiked = false,
                                uri = viewModel.movies.value!![i].poster
                            )
                        )
                    }
                    for (i in 0..<filmItems.size) {
                        for (j in 0..<viewModel.favMovies.value!!.size) {
                            if (viewModel.favMovies.value!![j].id == filmItems[i].id) {
                                filmItems[i].liked = true
                            }
                            if (i == 0) {
                                posterItems.add(PosterItem(viewModel.favMovies.value!![j].poster, viewModel.favMovies.value!![j].id))
                            }
                        }
                    }
                    initAdapterPosters(posterItems)
                    initAdapterFilms(filmItems)
                }
            }
        }
        lifecycleScope.launch {
            viewModel.moviesPageOne.collect {
                if (viewModel.moviesPageOne.value!!.isNotEmpty()) {
                    var cycleFilms = ArrayList<MovieElementModel>()
                    for (i in 0..<viewModel.moviesPageOne.value!!.size - 1) {
                        cycleFilms.add(viewModel.moviesPageOne.value!![i])
                    }
                    showCycleFilms(cycleFilms)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}