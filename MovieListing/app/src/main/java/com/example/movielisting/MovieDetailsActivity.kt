package com.example.movielisting

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.movielisting.AppConstant.ApiConstants
import com.example.movielisting.Controller.MovieController
import com.example.movielisting.Model.MovieDetailResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var imageViewBackdrop: ImageView
    private lateinit var textViewTitle: TextView
    private lateinit var textViewReleaseDate: TextView
    private lateinit var textViewOverview: TextView

    private val movieController = MovieController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val progressBar: ProgressBar = findViewById(R.id.progress_bar)


        imageViewBackdrop = findViewById(R.id.image_view_backdrop)
        textViewTitle = findViewById(R.id.text_view_title)
        textViewReleaseDate = findViewById(R.id.text_view_release_date)
        textViewOverview = findViewById(R.id.text_view_overview)

        val movieId = intent.getIntExtra("movie_id", -1)
        if (movieId != -1) {
            CoroutineScope(Dispatchers.Main).launch {
                progressBar.visibility = View.VISIBLE
                val movieDetails = movieController.getMovieDetails(movieId,this@MovieDetailsActivity)
                progressBar.visibility = View.GONE
                if (movieDetails != null) {
                    updateUI(movieDetails)
                } else {
                    Toast.makeText(this@MovieDetailsActivity, "Error getting movies details", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun updateUI(movieDetails: MovieDetailResponseModel) {
        Glide.with(this)
            .load(ApiConstants.imageUrl + movieDetails.backdrop_path)
            .placeholder(R.drawable.placeholder) // Placeholder image
            .into(imageViewBackdrop)

        textViewTitle.text = movieDetails.title
        textViewReleaseDate.text = movieDetails.release_date
        textViewOverview.text = movieDetails.overview
    }
}

