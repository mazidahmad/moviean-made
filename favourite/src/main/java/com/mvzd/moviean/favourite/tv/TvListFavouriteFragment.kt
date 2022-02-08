package com.mvzd.moviean.favourite.tv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvzd.moviean.databinding.FragmentTvListFavouriteBinding
import com.mvzd.moviean.core.domain.model.Tv
import com.mvzd.moviean.detail.tv.DetailTvActivity
import com.mvzd.moviean.di.FavouriteModuleDependencies
import com.mvzd.moviean.favourite.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class TvListFavouriteFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: FragmentTvListFavouriteBinding
    private val tvListViewModel: TvListFavouriteViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DaggerFavouriteTvComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    FavouriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        binding = FragmentTvListFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTvsFav.visibility = View.GONE
        binding.loadingFavTv.visibility = View.VISIBLE
        binding.noFavTv.visibility = View.GONE

        tvListViewModel.listFavouriteTv.observe(viewLifecycleOwner, { tvs ->
            if (tvs != null && tvs.isNotEmpty()){
                showRecyclerList(view.context, tvs)
            }else {
                binding.rvTvsFav.visibility = View.GONE
                binding.loadingFavTv.visibility = View.GONE
                binding.noFavTv.visibility = View.VISIBLE
            }
        })
    }

    private fun showRecyclerList(context: Context, tvs: List<Tv>) {
        binding.rvTvsFav.visibility = View.VISIBLE
        binding.loadingFavTv.visibility = View.GONE
        binding.noFavTv.visibility = View.GONE
        binding.rvTvsFav.layoutManager = LinearLayoutManager(context)
        val tvListAdapter = TvListFavouriteAdapter(tvs)
        binding.rvTvsFav.adapter = tvListAdapter
        tvListAdapter.setOnItemClickCallback(object :
            TvListFavouriteAdapter.OnItemClickCallback {
            override fun onItemClicked(tv: Tv) {
                val intent = Intent(activity?.application, DetailTvActivity::class.java)
                intent.putExtra(DetailTvActivity.EXTRA_TV, tv.id)
                startActivity(intent)
            }
        })
    }

    companion object
}