package ru.knowledge.mtstetaproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.knowledge.mtstetaproject.movies.MovieDetailsFragment
import ru.knowledge.mtstetaproject.movies.StartFragmentDetailsListener

class MainActivity : AppCompatActivity(), StartFragmentDetailsListener {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(
                R.id.main_fragment_container
        ) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onStartFragmentDetails(movieId: Int) {
        val args = bundleOf(MovieDetailsFragment.MOVIE_ID to movieId)
        navController.navigate(R.id.action_movies_to_details, args)
    }
}