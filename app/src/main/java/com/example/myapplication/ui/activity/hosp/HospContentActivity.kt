package com.example.myapplication.ui.activity.hosp

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.network.HospBanner
import com.example.myapplication.network.HospContent
import com.example.myapplication.network.ServiceCreate
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.activity_hosp_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospContentActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hosp_content)

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW
        h.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        h.setOnClickListener { finish() }

        val id = intent.getIntExtra("hospid",0)

        ServiceCreate.smartcityService.getHospContent(id).enqueue(object :Callback<HospContent>{
            override fun onFailure(p0: Call<HospContent>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HospContent>, p1: Response<HospContent>) {
                val body = p1.body()
                if (body != null){
                    h_content.text = body.data.brief
                }
            }

        })

        ServiceCreate.smartcityService.getHospBanner(id).enqueue(object :Callback<HospBanner>{
            override fun onFailure(p0: Call<HospBanner>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HospBanner>, p1: Response<HospBanner>) {
               val body = p1.body()
                if (body != null){
                    h_banner.adapter = object :BannerImageAdapter<HospBanner.Data>(body.data){
                        override fun onBindView(
                            p0: BannerImageHolder?,
                            p1: HospBanner.Data?,
                            p2: Int,
                            p3: Int
                        ) {
                            Glide.with(this@HospContentActivity).load(ServiceCreate.url + p1!!.imgUrl).into(p0!!.imageView)
                        }

                    }
                }
            }

        })

        h_btn.setOnClickListener {
            startActivity(Intent(this,HospUserActivity::class.java))
        }
    }
}