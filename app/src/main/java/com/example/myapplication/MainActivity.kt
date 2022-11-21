package com.example.myapplication

import android.app.Application
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.myapplication.extensions.sharedPreferences
import com.example.myapplication.ui.`fun`.FunFragment
import com.example.myapplication.ui.dj.DjFragment
import com.example.myapplication.ui.home.HomeFragment
import com.example.myapplication.ui.me.MeFragment
import com.example.myapplication.ui.news.NewsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var bottom1: BottomNavigationView

    companion object{

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SmartCityApplication.TOKEN = sharedPreferences.getString("token","").toString()

        bottom1 = findViewById(R.id.bottom)

        supportActionBar?.hide()
        window.statusBarColor = Color.TRANSPARENT

        loadFragment(HomeFragment())

        bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                }
                R.id.nav_fun -> {
                    loadFragment(FunFragment())
                }
                R.id.nav_dj -> {
                    loadFragment(DjFragment())
                }
                R.id.nav_news -> {
                    loadFragment(NewsFragment())
                }
                R.id.nav_me -> {
                    loadFragment(MeFragment())
                }

            }
            true
        }

    }

    private fun loadFragment(fm: Fragment) {
        val a = supportFragmentManager.beginTransaction()
        a.replace(R.id.fragment, fm)
        a.commit()
    }

}
