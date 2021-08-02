package ru.knowledge.mtstetaproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.knowledge.mtstetaproject.movies.MovieDetailsFragment
import ru.knowledge.mtstetaproject.movies.MoviesFragment
import ru.knowledge.mtstetaproject.movies.StartFragmentDetailsListener
import ru.knowledge.mtstetaproject.profile.ProfileFragment

class MainActivity : AppCompatActivity(), StartFragmentDetailsListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_home -> {
                    swapFragment(MoviesFragment.newInstance())
                    true
                }
                R.id.page_profile -> {
                    swapFragment(ProfileFragment.newInstance())
                    true
                }
                else -> false
            }
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.main_fragment_container, MoviesFragment.newInstance())
                commit()
            }
        }
    }

    override fun onStartFragmentDetails(movieId: Int) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_container, MovieDetailsFragment.newInstance(movieId))
            addToBackStack(null)
            commit()
        }
    }

    private fun swapFragment(fragment: Fragment) {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_container, fragment)
            commit()
        }
    }
}