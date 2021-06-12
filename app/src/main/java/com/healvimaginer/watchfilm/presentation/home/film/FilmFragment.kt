package com.healvimaginer.watchfilm.presentation.home.film

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.healvimaginer.watchfilm.core.data.vo.Resource
import com.healvimaginer.watchfilm.databinding.FragmentFilmBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FilmFragment : Fragment() {
    private lateinit var binding: FragmentFilmBinding
    private val viewModel : FilmViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val filmadapter = FilmAdapter()
            viewModel.film.observe(viewLifecycleOwner, {film ->
                if (film != null ) {
                    when (film) {
                        is Resource.loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.success -> {
                            binding.progressBar.visibility = View.GONE
                            film.data?.let { filmadapter.setFilm(it) }
                            filmadapter.notifyDataSetChanged()
                        }
                    }
                }
            })

            with(binding.rvFilm) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = filmadapter
            }
        }
    }

}