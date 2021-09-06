package ru.knowledge.mtstetaproject.movies

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.knowledge.mtstetaproject.R
import ru.knowledge.mtstetaproject.movies.data.MovieDto

abstract class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(movie: MovieDto, movieId: Long)
}

class DefaultMovieViewHolder(
    view: View,
    private val startFragmentDetailsListener: StartFragmentDetailsListener?
    ) : MovieViewHolder(view) {

    private val image: ImageView = view.findViewById(R.id.iv_item_movie)
    private val textTitle: TextView = view.findViewById(R.id.tv_item_movie_title)
    private val textDescription: TextView = view.findViewById(R.id.tv_item_movie_description)
    private val textAgeRating: TextView = view.findViewById(R.id.tv_item_movie_age_rating)
    private val starImagesRating = listOf<ImageView>(
        view.findViewById(R.id.iv_item_rating_star_1),
        view.findViewById(R.id.iv_item_rating_star_2),
        view.findViewById(R.id.iv_item_rating_star_3),
        view.findViewById(R.id.iv_item_rating_star_4),
        view.findViewById(R.id.iv_item_rating_star_5))
    private val iconFilledStar = ResourcesCompat.getDrawable(
        view.context.resources,
        R.drawable.ic_rating_filled_star_16,
        null)
    private val iconOutlineStar = ResourcesCompat.getDrawable(
        view.context.resources,
        R.drawable.ic_rating_outline_star_16,
        null)

    override fun bind(movie: MovieDto, movieId: Long) {
        image.load(movie.imageUrl)
        textTitle.text = movie.title
        textDescription.text = movie.description
        val mAgeRating = "${movie.ageRestriction}+"
        textAgeRating.text = mAgeRating
        val maxScore = if (movie.rateScore < 6) movie.rateScore else MAX_RATE_SCORE
        for (i in 0 until maxScore) {
            starImagesRating[i].setImageDrawable(iconFilledStar)
        }
        for (i in maxScore until MAX_RATE_SCORE) {
            starImagesRating[i].setImageDrawable(iconOutlineStar)
        }
        itemView.setOnClickListener{
            startFragmentDetailsListener?.onStartFragmentDetails(movieId)
        }
    }

    companion object{
        const val MAX_RATE_SCORE = 5
    }
}