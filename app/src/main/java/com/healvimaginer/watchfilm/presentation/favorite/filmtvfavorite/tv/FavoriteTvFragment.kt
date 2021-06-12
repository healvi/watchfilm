package com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.healvimaginer.watchfilm.core.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.databinding.FragmentFavoriteTvBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTvFragment : Fragment() {
    private val viewModel : FavTvViewModel by viewModel()
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

            binding.progressBar.visibility = View.VISIBLE
            val tvadapter = TvAdapter()

            viewModel.tv.observe(viewLifecycleOwner, {tv ->
                binding.progressBar.visibility = View.INVISIBLE
                val tvlist = ArrayList<TvEntity>()
                tv.map { it ->
                    val tva = TvEntity(
                        contentId=it.contentId,
                        name=it.name,
                        overview=it.overview,
                        popularity=it.popularity,
                        poster_path=it.poster_path,
                        backdrop_path=it.backdrop_path,
                        vote_average=it.vote_average,
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