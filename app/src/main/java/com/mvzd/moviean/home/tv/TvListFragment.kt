package com.mvzd.moviean.home.tv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvzd.moviean.core.data.source.remote.StatusResponse
import com.mvzd.moviean.databinding.FragmentTvListBinding
import com.mvzd.moviean.detail.tv.DetailTvActivity
import com.mvzd.moviean.core.domain.model.Tv
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvListFragment : Fragment() {
    private lateinit var binding: FragmentTvListBinding
    private val tvListViewModel: TvListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvListViewModel.listTv.observe(viewLifecycleOwner, { tvs ->
            when(tvs.status) {
                StatusResponse.SUCCESS -> showRecyclerList(view.context, tvs.data!!)
            }
        })
    }

    private fun showRecyclerList(context: Context, tvs: List<Tv>) {
        binding.rvTvs.layoutManager = LinearLayoutManager(context)
        val tvListAdapter = TvListAdapter(tvs)
        binding.rvTvs.adapter = tvListAdapter
        tvListAdapter.setOnItemClickCallback(object : TvListAdapter.OnItemClickCallback {
            override fun onItemClicked(tv: Tv) {
                val intent = Intent(activity?.application, DetailTvActivity::class.java)
                intent.putExtra(DetailTvActivity.EXTRA_TV, tv.id)
                startActivity(intent)
            }
        })
    }

    companion object
}