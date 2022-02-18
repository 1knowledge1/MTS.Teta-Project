package ru.knowledge.mtstetaproject.movies.movies.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.knowledge.mtstetaproject.R
import ru.knowledge.mtstetaproject.movies.StartFragmentDetailsListener
import ru.knowledge.mtstetaproject.movies.database.dto.MovieDto

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
        holder.bind(mMovies[position], mMovies[position].id)
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