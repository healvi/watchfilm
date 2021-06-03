package com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.film

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.healvimaginer.watchfilm.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.databinding.FragmentFavoriteFilmBinding
import com.healvimaginer.watchfilm.domain.viewModelFactory.ViewModelFactory


class FavoriteFilmFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteFilmBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteFilmBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this,factory)[FavFilmViewModel::class.java]

            binding.progressBar.visibility = View.VISIBLE
            val filmadapter = FilmAdapter()


            viewModel.getAllFilmPag().observe(viewLifecycleOwner, {film ->
                binding.progressBar.visibility = View.INVISIBLE
                val filmlist = ArrayList<FilmsEntity>()
                film.map { filmEntity ->
                    val filma = FilmsEntity(
                        contentId = filmEntity.contentId,
                        description = filmEntity.description,
                        image = filmEntity.image,
                        anggaran = filmEntity.anggaran,
                        director = filmEntity.director,
                        rilis = filmEntity.rilis,
                        pendapatan = filmEntity.pendapatan,
                        title = filmEntity.title
                    )
                    filmlist.add(filma)
                }
                filmadapter.submitList(film)
                filmadapter.notifyDataSetChanged()
            })

            with(binding.rvFilm) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = filmadapter
            }
        }
    }

}