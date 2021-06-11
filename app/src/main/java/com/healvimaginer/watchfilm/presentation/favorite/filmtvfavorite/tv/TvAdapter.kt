package com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.healvimaginer.watchfilm.R
import com.healvimaginer.watchfilm.databinding.ItemListTvBinding
import com.healvimaginer.watchfilm.domain.model.Tv
import com.healvimaginer.watchfilm.presentation.detail.tv.DetailsTvActivity

class TvAdapter: RecyclerView.Adapter<TvAdapter.ViewHolder>() {
    private val listTv = ArrayList<Tv>()
    fun setTv(film: List<Tv>){
        this.listTv.clear()
        this.listTv.addAll(film)
    }

    class ViewHolder(private val binding: ItemListTvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvlist: Tv) {
            with(binding) {
                tvItemTitle.text = tvlist.name
                tvItemDate.text = tvlist.first_air_date
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailsTvActivity::class.java)
                    intent.putExtra(DetailsTvActivity.EXTRA_TV,tvlist.contentId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/"+tvlist.poster_path)
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

    override fun getItemCount(): Int {
        return listTv.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvlist = listTv[position]
        holder.bind(tvlist)
    }
}