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
                tv.map { tvEntity ->
                    val tva = TvEntity(
                        contentId = tvEntity.contentId,
                        description = tvEntity.description,
                        image = tvEntity.image,
                        kreator = tvEntity.kreator,
                        network = tvEntity.network,
                        rilis = tvEntity.rilis,
                        status = tvEntity.status,
                        title = tvEntity.title
                    )
                    tvlist.add(tva)
                }
                tvadapter.submitList(tv)
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