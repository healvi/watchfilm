package com.heavlimaginer.watchfilm.favorite.filmtvfavorite.film

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.healvimaginer.watchfilm.core.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.databinding.FragmentFavoriteFilmBinding
import com.healvimaginer.watchfilm.presentation.favorite.filmtvfavorite.film.FilmAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFilmFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteFilmBinding
    private val viewModel: FavFilmViewModel by viewModel()
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

            binding.progressBar.visibility = View.VISIBLE
            val filmadapter = FilmAdapter()


            viewModel.film.observe(viewLifecycleOwner, {film ->
                binding.progressBar.visibility = View.INVISIBLE
                val filmlist = ArrayList<FilmsEntity>()
                film.map { data ->
                    val filma = FilmsEntity(
                        contentId=data.contentId,
                        title=data.title,
                        overview=data.overview,
                        popularity=data.popularity,
                        poster_path=data.poster_path,
                        backdrop_path=data.backdrop_path,
                        vote_average=data.vote_average,
                        release_date=data.release_date,
                    )
                    filmlist.add(filma)
                }
                filmadapter.setFilm(film)
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