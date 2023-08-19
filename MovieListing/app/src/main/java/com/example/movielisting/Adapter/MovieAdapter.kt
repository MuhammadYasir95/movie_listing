package com.example.movielisting.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielisting.AppConstant.ApiConstants
import com.example.movielisting.Model.Result
import com.example.movielisting.R
import java.util.Locale

class MovieAdapter(private var movies: List<Result>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(), Filterable {

    private var filteredMovies: List<Result> = movies.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = filteredMovies[position]

        holder.titleTextView.text = currentMovie.title
        holder.overviewTextView.text = currentMovie.overview

        // Load movie poster using Glide or your preferred image loading library
        Glide.with(holder.itemView)
            .load(ApiConstants.imageUrl + currentMovie.poster_path)
            .placeholder(R.drawable.placeholder) // Placeholder image
            .into(holder.posterImageView)
    }

    override fun getItemCount(): Int {
        return filteredMovies.size
    }

    fun updateData(newMovies: List<Result>) {
        movies = newMovies
        filteredMovies = newMovies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val titleTextView: TextView = itemView.findViewById(R.id.text_view_title)
        val overviewTextView: TextView = itemView.findViewById(R.id.text_view_overview)
        val posterImageView: ImageView = itemView.findViewById(R.id.image_view_poster)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(movies[position])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(movie: Result)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredResults = if (constraint.isNullOrEmpty()) {
                    movies.toMutableList()
                } else {
                    val query = constraint.toString().lowercase()
                    movies.filter { it.title?.lowercase()?.contains(query) == true }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredResults
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredMovies = results?.values as? List<Result> ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }
}
