package ru.knowledge.mtstetaproject.movies.movies

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import ru.knowledge.mtstetaproject.App
import ru.knowledge.mtstetaproject.R
import ru.knowledge.mtstetaproject.movies.*
import ru.knowledge.mtstetaproject.movies.database.dto.GenreDto
import ru.knowledge.mtstetaproject.movies.movies.recycler.ItemOffsetDecoration
import ru.knowledge.mtstetaproject.movies.movies.recycler.MoviesAdapter

class MoviesFragment : Fragment() {

    private var startFragmentDetailsListener: StartFragmentDetailsListener? = null
    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory((activity?.application as App).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.swipe_container_movies)
        val moviesRecycler = view.findViewById<RecyclerView>(R.id.movie_recycler)
        val moviesAdapter = MoviesAdapter(startFragmentDetailsListener)
        moviesRecycler.adapter = moviesAdapter
        moviesRecycler.layoutManager = GridLayoutManager(context, COLUMNS_NUMBER)
        val itemDecoration = ItemOffsetDecoration(
            COLUMNS_NUMBER,
            context?.resources?.getDimensionPixelSize(R.dimen.dimen_150) ?: DEFAULT_ITEM_SIZE
        )
        moviesRecycler.addItemDecoration(itemDecoration)

        movieViewModel.movieList.observe(viewLifecycleOwner, { movies ->
            if (movies != null && movies.isNotEmpty()) {
                view.findViewById<ProgressBar>(R.id.pb_movie_list).visibility = View.INVISIBLE
            }
            moviesAdapter.setMovies(movies ?: emptyList())
            swipeContainer.isRefreshing = false
        })

        var isNotInitGenresChipGroup = true
        movieViewModel.genreList.observe(viewLifecycleOwner, { genres ->
            if (isNotInitGenresChipGroup) {
                initGenreChipGroup(view, genres)
                isNotInitGenresChipGroup = false
            }
        })

        movieViewModel.errorState.observe(viewLifecycleOwner, { message ->
            if (message.isNotEmpty()) {
                view.findViewById<ProgressBar>(R.id.pb_movie_list).visibility = View.INVISIBLE
                Toast
                    .makeText(view.context, "Не удалось обновить: $message", Toast.LENGTH_SHORT)
                    .show()
                swipeContainer.isRefreshing = false
                movieViewModel.resetError()
            }
            Log.d("MyObserver", "Вызван observer, message=$message")
        })

        swipeContainer.setOnRefreshListener {
            movieViewModel.refreshMovies()
        }

        movieViewModel.getGenres()
        movieViewModel.getMovies()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is StartFragmentDetailsListener) {
            startFragmentDetailsListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        startFragmentDetailsListener = null
    }

    private fun initGenreChipGroup(view: View, genres: List<GenreDto>) {
        val chipGroup = view.findViewById<ChipGroup>(R.id.genre_chip_group)
        for (genre in genres) {
            chipGroup.addView(Chip(context).apply {
                text = genre.name.lowercase()
                setOnCheckedChangeListener { view, isChecked ->
                    if (isChecked) {
                        movieViewModel.genresChecked.add(genre.id)
                        movieViewModel.getMovies()
                    } else {
                        movieViewModel.genresChecked.remove(genre.id)
                        movieViewModel.getMovies()
                    }
                }
                if (genre.id in movieViewModel.genresChecked) {
                    isChecked = true
                }
            })
        }

        val paramFirstView = chipGroup.getChildAt(0).layoutParams as ChipGroup.LayoutParams
        val margin = context?.resources?.getDimensionPixelSize(R.dimen.dimen_20)
        paramFirstView.apply {
            setMargins(margin ?: leftMargin, topMargin, rightMargin, bottomMargin)
        }
        chipGroup.getChildAt(0).layoutParams = paramFirstView
        val paramLastView = chipGroup.getChildAt(genres.size - 1).layoutParams as ChipGroup.LayoutParams
        paramLastView.apply {
            setMargins(leftMargin, topMargin, margin ?: rightMargin, bottomMargin)
        }
        chipGroup.getChildAt(genres.size - 1).layoutParams = paramLastView
    }

    companion object {
        const val COLUMNS_NUMBER = 2
        const val DEFAULT_ITEM_SIZE = 150

        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }
    }
}