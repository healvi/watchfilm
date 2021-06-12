package com.healvimaginer.watchfilm.presentation.home.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.healvimaginer.watchfilm.core.data.vo.Resource
import com.healvimaginer.watchfilm.databinding.FragmentTvBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TvFragment : Fragment() {
    private lateinit var binding:FragmentTvBinding
    private val viewModel : TvViewModel by viewModel()
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

            val tvadapter = TvAdapter()

            viewModel.tv.observe(viewLifecycleOwner, {tv ->
                if (tv != null ) {
                    when (tv) {
                        is Resource.loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.success -> {
                            binding.progressBar.visibility = View.GONE
                            tv.data?.let { tvadapter.setTv(it) }
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