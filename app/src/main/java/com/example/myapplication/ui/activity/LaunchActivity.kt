package com.example.myapplication.ui.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.extensions.sharedPreferences
import com.example.myapplication.extensions.showToast
import com.example.myapplication.ui.account.LoginActivity
import com.example.myapplication.ui.set.IPActivity
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnPageChangeListener
import kotlinx.android.synthetic.main.activity_launch.*

class LaunchActivity : AppCompatActivity(),View.OnClickListener {

    private val array = arrayListOf(
        R.drawable.launch01,
        R.drawable.launch02,
        R.drawable.launch03,
        R.drawable.launch04,
        R.drawable.launch05
    )
    private var launchBanner:Banner<Int,BannerImageAdapter<Int>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        supportActionBar?.hide()

        launchBanner = findViewById(R.id.l_banner)

        btn_net.setOnClickListener(this)
        go_home.setOnClickListener(this)

        launchBanner?.apply {
            setAdapter(object :BannerImageAdapter<Int>(array){
                override fun onBindView(p0: BannerImageHolder?, p1: Int?, p2: Int, p3: Int) {
                    Glide.with(p0!!.imageView).load(array[p2]).into(p0.imageView)
                }

            },false)
            isAutoLoop(false)

            addOnPageChangeListener(object :OnPageChangeListener{
                override fun onPageScrollStateChanged(p0: Int) {
                }

                override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                   if (p0 == array.size-1){
                       btn_net.visibility = View.VISIBLE
                       go_home.visibility = View.VISIBLE
                   }else{
                       btn_net.visibility = View.GONE
                       go_home.visibility = View.GONE
                   }
                }

                override fun onPageSelected(p0: Int) {
                }

            })
            indicator = CircleIndicator(this@LaunchActivity)
            setIndicatorSelectedColor(Color.YELLOW)
            setIndicatorWidth(20,30)
        }

    }

    override fun onClick(v: View?) {

        when(v?.id){

            R.id.go_home -> {
                val ip = sharedPreferences.getString("ip","")
                val dk = sharedPreferences.getString("dk","")
                if (TextUtils.isEmpty(ip) && TextUtils.isEmpty(dk)){
                    "请设置网络IP".showToast()
                }else{
                    val token = sharedPreferences.getString("token","")
                    if (TextUtils.isEmpty(token)){
                        startActivity(Intent(this,LoginActivity::class.java))
                        finish()
                    }else{
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                }

            }

            R.id.btn_net -> {
                IPActivity(this).apply {
                    showAtLocation(v,Gravity.BOTTOM,width/2,0)
                }
            }

        }

    }
}