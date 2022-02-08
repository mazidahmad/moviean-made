package com.mvzd.moviean

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.mvzd.moviean.databinding.ActivityMainBinding
import com.mvzd.moviean.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setSupportActionBar(binding.appBarMain.toolbar)

        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.appBarMain.toolbar, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, HomeFragment()).commit()
        }

        setContentView(binding.root)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        var title = getString(R.string.app_name)
        when (item.itemId) {
            R.id.nav_home -> {
                title = getString(R.string.app_name)
                fragment = HomeFragment()
            }
            R.id.nav_favorite -> {
                title = "Favourite"
                fragment = Class.forName("com.mvzd.moviean.favourite.FavouriteFragment").newInstance() as Fragment
            }
        }
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
        }
        supportActionBar?.title = title

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}