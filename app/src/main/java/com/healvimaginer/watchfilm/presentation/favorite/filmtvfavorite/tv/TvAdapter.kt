package com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.tv

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
import com.healvimaginer.watchfilm.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.databinding.ItemListTvBinding
import com.healvimaginer.watchfilm.presentation.tv.DetailsTvActivity

class TvAdapter: PagedListAdapter<FavoriteTvEntity, TvAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<FavoriteTvEntity> = object : DiffUtil.ItemCallback<FavoriteTvEntity>() {
            override fun areItemsTheSame(oldNote: FavoriteTvEntity, newNote: FavoriteTvEntity): Boolean {
                return oldNote.title == newNote.title && oldNote.description == newNote.description && oldNote.rilis == newNote.rilis && oldNote.image == newNote.image
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldNote: FavoriteTvEntity, newNote: FavoriteTvEntity): Boolean {
                return oldNote == newNote
            }
        }
    }

    class ViewHolder(private val binding: ItemListTvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvlist: FavoriteTvEntity) {
            with(binding) {
                tvItemTitle.text = tvlist.title
                tvItemDate.text = tvlist.rilis
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailsTvActivity::class.java)
                    intent.putExtra(DetailsTvActivity.EXTRA_TV,tvlist.contentId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(tvlist.image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemTv = ItemListTvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemTv)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) as FavoriteTvEntity)
    }

}