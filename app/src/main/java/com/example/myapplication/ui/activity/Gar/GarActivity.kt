package com.example.myapplication.ui.activity.Gar

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.network.AdBanner
import com.example.myapplication.network.NewsType
import com.example.myapplication.network.ServiceCreate
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.activity_gar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GarActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gar)

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW
        c.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        c.setOnClickListener { finish() }

        btn_sv.setOnClickListener {
            startActivity(Intent(this,GarSvActivity::class.java))
        }

        ServiceCreate.smartcityService.getAdBanner().enqueue(object :Callback<AdBanner>{
            override fun onFailure(p0: Call<AdBanner>, p1: Throwable) {
                Log.i("image","1111",p1)
            }

            override fun onResponse(p0: Call<AdBanner>, p1: Response<AdBanner>) {
                val body =p1.body()
                if (body != null){
                    Log.d("image","${body.msg}")
                    g_banner.adapter = object :BannerImageAdapter<AdBanner.Data>(body.data){
                        override fun onBindView(
                            p0: BannerImageHolder?,
                            p1: AdBanner.Data?,
                            p2: Int,
                            p3: Int
                        ) {
                            Glide.with(p0!!.imageView).load(ServiceCreate.url + p1!!.imgUrl).into(p0.imageView)

                        }

                    }
                }
            }

        })
        ServiceCreate.smartcityService.getNewsType().enqueue(object :Callback<NewsType>{
            override fun onFailure(p0: Call<NewsType>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<NewsType>, p1: Response<NewsType>) {
               val body = p1.body()
                if (body != null){
                    Log.d("body","${body.msg}")
                    val adapter = GarPagerAdapter(this@GarActivity.supportFragmentManager,body.rows)
                    v2.adapter = adapter
                    t2.setupWithViewPager(v2)
                }
            }

        })

    }
}