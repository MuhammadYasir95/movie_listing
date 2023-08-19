package com.example.movielisting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movielisting.Adapter.MovieAdapter
import com.example.movielisting.Controller.MovieController
import com.example.movielisting.Model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListActivity : AppCompatActivity() {

    private val movieController = MovieController()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_movies)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val searchView: SearchView = findViewById(R.id.search_view)
        val textViewNoData = findViewById<TextView>(R.id.text_view_no_data)
        val progressBar: ProgressBar = findViewById(R.id.progress_bar)


        movieAdapter = MovieAdapter(emptyList(), object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(movie: Result) {
                val intent = Intent(this@MovieListActivity, MovieDetailsActivity::class.java)
                intent.putExtra("movie_id", movie.id?.toInt() ?: -1)
                startActivity(intent)
            }
        })

        recyclerView.adapter = movieAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                movieAdapter.filter.filter(newText)
                return true
            }
        })


        CoroutineScope(Dispatchers.Main).launch {
            progressBar.visibility = View.VISIBLE
            val response = movieController.getPopularMovies(this@MovieListActivity)
            progressBar.visibility = View.GONE
            if (response != null) {
                val movies = response.results
                if (!movies.isNullOrEmpty()) {
                    movieAdapter.updateData(movies)
                    textViewNoData.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    searchView.visibility = View.VISIBLE
                }else{
                    textViewNoData.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    searchView.visibility = View.GONE
                }
            } else {
                textViewNoData.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
                searchView.visibility = View.GONE
                Toast.makeText(this@MovieListActivity, "No records found", Toast.LENGTH_SHORT).show()
            }
        }

    }
}