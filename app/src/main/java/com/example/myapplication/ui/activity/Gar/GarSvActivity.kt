package com.example.myapplication.ui.activity.Gar

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.GarLjBanner
import com.example.myapplication.network.GarLjType
import com.example.myapplication.network.HotTitle
import com.example.myapplication.network.ServiceCreate
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.activity_gar_content.*
import kotlinx.android.synthetic.main.activity_gar_sv.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GarSvActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gar_sv)

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW

        b.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        b.setOnClickListener { finish() }

        ServiceCreate.smartcityService.getLjBanner().enqueue(object :Callback<GarLjBanner>{
            override fun onFailure(p0: Call<GarLjBanner>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<GarLjBanner>, p1: Response<GarLjBanner>) {
               val body = p1.body()
                if (body != null){
                    lj_banner.adapter = object :BannerImageAdapter<GarLjBanner.Data>(body.data){
                        override fun onBindView(
                            p0: BannerImageHolder?,
                            p1: GarLjBanner.Data?,
                            p2: Int,
                            p3: Int
                        ) {
                            Glide.with(p0!!.imageView).load(ServiceCreate.url + p1!!.imgUrl).into(p0.imageView)
                        }
                    }

                }
            }
        })
        sv_lj.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                ServiceCreate.smartcityService.getGarLjType(newText).enqueue(object :Callback<GarLjType>{
                    override fun onFailure(p0: Call<GarLjType>, p1: Throwable) {
                    }

                    override fun onResponse(p0: Call<GarLjType>, p1: Response<GarLjType>) {
                        val body = p1.body()
                        if(body != null){
                            val adapter  =ItemAdapter(R.layout.item_service,body.rows,LL::class.java)
                            rv_lj_type.layoutManager = GridLayoutManager(this@GarSvActivity,4)
                            rv_lj_type.adapter  =adapter
                        }
                    }

                })
                return false
            }

        })

        ServiceCreate.smartcityService.getHotTitle().enqueue(object :Callback<HotTitle>{
            override fun onFailure(p0: Call<HotTitle>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HotTitle>, p1: Response<HotTitle>) {
               val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_hot_title,body.data,MM::class.java,10)
                    rv_hot_title.layoutManager =GridLayoutManager(this@GarSvActivity,5)
                    rv_hot_title.adapter = adapter
                }
            }

        })
    }
}

class LL(view:View):ItemAdapter.MyViewHolder(view){
    val name:TextView = view.findViewById(R.id.s_text)
    val iamge:ImageView = view.findViewById(R.id.s_image)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        Glide.with(iamge).load(ServiceCreate.url+(data[position] as GarLjType.Row).imgUrl).into(iamge)
        name.text = (data[position] as GarLjType.Row).name
    }
}

class MM(view: View):ItemAdapter.MyViewHolder(view){
    val name:TextView = view.findViewById(R.id.hot)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as HotTitle.Data).keyword
    }
}