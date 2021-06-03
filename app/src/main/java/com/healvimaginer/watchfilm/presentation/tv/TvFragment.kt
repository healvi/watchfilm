package com.healvimaginer.watchfilm.presentation.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.healvimaginer.watchfilm.databinding.FragmentTvBinding
import com.healvimaginer.watchfilm.domain.viewModelFactory.ViewModelFactoryTv
import com.healvimaginer.watchfilm.domain.vo.Status


class TvFragment : Fragment() {
    private lateinit var binding:FragmentTvBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactoryTv.getInstance(requireContext())
            val viewModel = ViewModelProvider(this,factory)[TvViewModel::class.java]

            val tvadapter = TvAdapter()

            viewModel.getTv().observe(viewLifecycleOwner, {tv ->
                if (tv != null ) {
                    when (tv.status) {
                        Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            tv.data?.let { tvadapter.submitList(it) }
                            tvadapter.notifyDataSetChanged()
                        }
                    }
                }
            })


            with(binding.rvTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvadapter
            }
        }
    }

}