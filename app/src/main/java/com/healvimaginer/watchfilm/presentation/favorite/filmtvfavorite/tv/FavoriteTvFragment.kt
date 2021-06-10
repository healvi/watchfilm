package com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.healvimaginer.watchfilm.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.databinding.FragmentFavoriteTvBinding
import com.healvimaginer.watchfilm.domain.viewModelFactory.ViewModelFactoryTv


class FavoriteTvFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteTvBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteTvBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactoryTv.getInstance(requireContext())
            val viewModel = ViewModelProvider(this,factory)[FavTvViewModel::class.java]

            binding.progressBar.visibility = View.VISIBLE
            val tvadapter = TvAdapter()

            viewModel.getAllTvPag().observe(viewLifecycleOwner, {tv ->
                binding.progressBar.visibility = View.INVISIBLE
                val tvlist = ArrayList<TvEntity>()
                tv.map { it ->
                    val tva = TvEntity(
                        contentId=it.contentId,
                        title=it.title,
                        name=it.name,
                        overview=it.overview,
                        popularity=it.popularity,
                        poster_path=it.poster_path,
                        backdrop_path=it.backdrop_path,
                        vote_average=it.vote_average,
                        release_date=it.release_date,
                        first_air_date=it.first_air_date
                    )
                    tvlist.add(tva)
                }
                tvadapter.setTv(tv)
                tvadapter.notifyDataSetChanged()
            })


            with(binding.rvTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvadapter
            }
        }
    }


}