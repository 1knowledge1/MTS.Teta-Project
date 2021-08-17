package ru.knowledge.mtstetaproject.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import ru.knowledge.mtstetaproject.R
import ru.knowledge.mtstetaproject.movies.data.MovieDto

class MovieDetailsFragment : Fragment() {

    private lateinit var movieViewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel = activity?.let {
            ViewModelProvider(it)[MovieDetailsViewModel::class.java]
        } ?: throw Exception("Activity is null")

        view.findViewById<ProgressBar>(R.id.pb_movie_details).visibility = View.VISIBLE
        view.findViewById<CoordinatorLayout>(R.id.layout_movie_details).visibility = View.INVISIBLE

        val movieId = arguments?.getInt(MOVIE_ID) ?: 0
        movieViewModel.movie.observe(viewLifecycleOwner, { movie ->
            if (movieId == movie.id) {
                initViews(view, movie)
            }
        })
        movieViewModel.getMovieById(movieId)
    }

    private fun initViews(view: View, movie: MovieDto) {
        val ageRestriction = "${movie.ageRestriction}+"
        view.apply{
            findViewById<ImageView>(R.id.iv_movie_details_poster).load(movie.imageUrl)
            findViewById<TextView>(R.id.tv_movie_genre).text = movie.genre.name.lowercase()
            findViewById<TextView>(R.id.tv_movie_release_date).text = movie.releaseDate
            findViewById<TextView>(R.id.tv_movie_age_rating).text = ageRestriction
            findViewById<TextView>(R.id.tv_movie_title).text = movie.title
            findViewById<TextView>(R.id.tv_movie_description).text = movie.description
            findViewById<ImageView>(R.id.iv_actor_1).load(movie.actors[0].imageUrl)
            findViewById<TextView>(R.id.tv_actor_name_1).text = movie.actors[0].name
            findViewById<ImageView>(R.id.iv_actor_2).load(movie.actors[1].imageUrl)
            findViewById<TextView>(R.id.tv_actor_name_2).text = movie.actors[1].name
            findViewById<ImageView>(R.id.iv_actor_3).load(movie.actors[2].imageUrl)
            findViewById<TextView>(R.id.tv_actor_name_3).text = movie.actors[2].name
        }
        val iconStar = ResourcesCompat.getDrawable(
            view.context.resources,
            R.drawable.ic_rating_filled_star_16,
            null)
        val starImagesRating = listOf<ImageView>(
            view.findViewById(R.id.iv_rating_star_1),
            view.findViewById(R.id.iv_rating_star_2),
            view.findViewById(R.id.iv_rating_star_3),
            view.findViewById(R.id.iv_rating_star_4),
            view.findViewById(R.id.iv_rating_star_5))
        val maxScore = if (movie.rateScore < 6) movie.rateScore else MAX_RATE_SCORE
        for (i in 0 until maxScore) {
            starImagesRating[i].setImageDrawable(iconStar)
        }
        view.findViewById<ProgressBar>(R.id.pb_movie_details).visibility = View.INVISIBLE
        view.findViewById<CoordinatorLayout>(R.id.layout_movie_details).visibility = View.VISIBLE
    }

    companion object {
        const val MAX_RATE_SCORE = 5
        const val MOVIE_ID = "movieID"

        fun newInstance(movieId: Int): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val args = Bundle()
            args.putInt(MOVIE_ID, movieId)
            fragment.arguments = args
            return fragment
        }
    }
}