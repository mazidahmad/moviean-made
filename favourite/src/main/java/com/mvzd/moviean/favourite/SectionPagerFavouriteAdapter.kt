package com.mvzd.moviean.favourite

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mvzd.moviean.favourite.movie.MovieListFavouriteFragment
import com.mvzd.moviean.favourite.tv.TvListFavouriteFragment

class SectionPagerFavouriteAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> {
                fragment = MovieListFavouriteFragment()
            }
            1 -> {
                fragment = TvListFavouriteFragment()
            }
        }
        return fragment as Fragment
    }
}