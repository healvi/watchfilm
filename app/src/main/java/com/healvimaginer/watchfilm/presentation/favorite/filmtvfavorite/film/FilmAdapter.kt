package com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.film

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.healvimaginer.watchfilm.R
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.databinding.ItemListFilmBinding
import com.healvimaginer.watchfilm.presentation.film.DetailsFilmActivity


class FilmAdapter :  PagedListAdapter<FavoriteFilmEntity, FilmAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<FavoriteFilmEntity> = object : DiffUtil.ItemCallback<FavoriteFilmEntity>() {
            override fun areItemsTheSame(oldNote: FavoriteFilmEntity, newNote: FavoriteFilmEntity): Boolean {
                return oldNote.title == newNote.title && oldNote.description == newNote.description && oldNote.rilis == newNote.rilis && oldNote.image == newNote.image
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldNote: FavoriteFilmEntity, newNote: FavoriteFilmEntity): Boolean {
                return oldNote == newNote
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) as FavoriteFilmEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemListMainBinding = ItemListFilmBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemListMainBinding)
    }

    class ViewHolder(private val binding: ItemListFilmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(filmlist: FavoriteFilmEntity) {
            with(binding) {
                tvItemTitle.text = filmlist.title
                tvItemDate.text = filmlist.rilis
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailsFilmActivity::class.java)
                    intent.putExtra(DetailsFilmActivity.EXTRA_FILM,filmlist.contentId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(filmlist.image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPoster)
            }
        }
    }
}