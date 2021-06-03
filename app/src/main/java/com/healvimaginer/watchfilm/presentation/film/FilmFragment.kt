package com.healvimaginer.watchfilm.presentation.film

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.healvimaginer.watchfilm.domain.viewModelFactory.ViewModelFactory
import com.healvimaginer.watchfilm.databinding.FragmentFilmBinding
import com.healvimaginer.watchfilm.domain.vo.Status


class FilmFragment : Fragment() {
    private lateinit var binding: FragmentFilmBinding
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
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this,factory)[FilmViewModel::class.java]
            val filmadapter = FilmAdapter()
            viewModel.getFilm().observe(viewLifecycleOwner, {film ->
                if (film != null ) {
                    when (film.status) {
                        Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            film.data?.let { filmadapter.submitList(it) }
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