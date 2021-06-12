package com.healvimaginer.watchfilm.presentation.home.film

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.healvimaginer.watchfilm.R
import com.healvimaginer.watchfilm.core.domain.model.Film
import com.healvimaginer.watchfilm.core.databinding.ItemListFilmBinding
import com.healvimaginer.watchfilm.presentation.detail.film.DetailsFilmActivity

class FilmAdapter : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
    private val listFilm = ArrayList<Film>()
    fun setFilm(film: List<Film>){
        this.listFilm.clear()
        this.listFilm.addAll(film)
    }

    class ViewHolder(private val binding: ItemListFilmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(filmlist: Film) {
            with(binding) {
                tvItemTitle.text = filmlist.title
                tvItemDate.text = filmlist.release_date
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailsFilmActivity::class.java)
                    intent.putExtra(DetailsFilmActivity.EXTRA_FILM,filmlist.contentId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/"+filmlist.poster_path)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemListMainBinding = ItemListFilmBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemListMainBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filmlist = listFilm[position]
        holder.bind(filmlist)
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }

}