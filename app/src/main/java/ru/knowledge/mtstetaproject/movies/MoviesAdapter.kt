package ru.knowledge.mtstetaproject.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.knowledge.mtstetaproject.R
import ru.knowledge.mtstetaproject.movies.data.MovieDto

class MoviesAdapter(
    private val startListener: StartFragmentDetailsListener?
    ) : RecyclerView.Adapter<MovieViewHolder>() {

    private var mMovies: MutableList<MovieDto> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return DefaultMovieViewHolder(view, startListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(mMovies[position], position)
    }

    override fun getItemCount(): Int = mMovies.size

    fun setMovies(movies: List<MovieDto>) {
        if (mMovies.isNotEmpty()) {
            mMovies.clear()
        }
        mMovies.addAll(movies)
        notifyDataSetChanged()
    }
}