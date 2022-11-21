package com.example.myapplication.ui.home

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.network.NewContent
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_new_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewContentActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_content)
        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW

        val id = intent.getIntExtra("bannerid",0)

        ServiceCreate.smartcityService.getNewContent(id).enqueue(object :Callback<NewContent>{
            override fun onFailure(p0: Call<NewContent>, p1: Throwable) {
                Log.d("bannerid","111111")
            }

            override fun onResponse(p0: Call<NewContent>, p1: Response<NewContent>) {
                val body = p1.body()
                if (body != null){
                    Log.d("bannerid","${body.data}")
                    news_title.text = body.data.title
                    news_content.text = Html.fromHtml(body.data.content)
                    Glide.with(this@NewContentActivity).load(ServiceCreate.url + body.data.cover).into(news_image)
                }
            }

        })

    }
}