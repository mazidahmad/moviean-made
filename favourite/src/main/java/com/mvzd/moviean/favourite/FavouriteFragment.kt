package com.mvzd.moviean.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mvzd.moviean.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showViewPager()
    }

    private fun showViewPager() {
        val sectionPagerAdapter = SectionPagerFavouriteAdapter(this)
        val viewPager: ViewPager2 = binding.viewPagerFavourite
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = binding.tabsFavourite
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()
    }

    companion object {
        private val TAB_TITLES = arrayOf("Movies", "TV Show")
    }
}