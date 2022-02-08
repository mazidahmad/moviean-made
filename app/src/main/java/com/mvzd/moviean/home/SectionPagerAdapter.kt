package com.mvzd.moviean.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mvzd.moviean.home.movie.MovieListFragment
import com.mvzd.moviean.home.tv.TvListFragment

class SectionPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> {
                fragment = MovieListFragment()
            }
            1 -> {
                fragment = TvListFragment()
            }
        }
        return fragment as Fragment
    }
}