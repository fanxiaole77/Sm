package com.example.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.*
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Sv.setOnClickListener {
            startActivity(Intent(activity,SvActivity::class.java))
        }

        ServiceCreate.smartcityService.getHomeBanner().enqueue(object :Callback<HomeBanner>{
            override fun onFailure(p0: Call<HomeBanner>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HomeBanner>, p1: Response<HomeBanner>) {
               val body = p1.body()
                if (body != null){
                    homeBanner.adapter = object :BannerImageAdapter<HomeBanner.Row>(body.rows){
                        override fun onBindView(
                            p0: BannerImageHolder?,
                            p1: HomeBanner.Row?,
                            p2: Int,
                            p3: Int
                        ) {
                            Glide.with(p0!!.imageView).load(ServiceCreate.url + p1!!.advImg).into(p0.imageView)
                        }
                    }
                    homeBanner.setOnBannerListener(object :OnBannerListener<HomeBanner.Row>{
                        override fun OnBannerClick(p0: HomeBanner.Row?, p1: Int) {
                            val intent = Intent(this@HomeFragment.requireActivity(),NewContentActivity::class.java)
                            intent.putExtra("bannerid",p0!!.targetId)
                            startActivity(intent)
                        }

                    })
                }
            }

        })

        ServiceCreate.smartcityService.getHomeService().enqueue(object :Callback<HomeService>{
            override fun onFailure(p0: Call<HomeService>, p1: Throwable) {

            }

            override fun onResponse(p0: Call<HomeService>, p1: Response<HomeService>) {
                val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_service,body.rows,AA::class.java,10,
                        listOf(activity))
                    rv_service.layoutManager = GridLayoutManager(this@HomeFragment.requireActivity(),5)
                    rv_service.adapter = adapter
                }
            }

        })

        ServiceCreate.smartcityService.getHot("Y").enqueue(object :Callback<NewsList>{
            override fun onFailure(p0: Call<NewsList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<NewsList>, p1: Response<NewsList>) {
               val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_hot,body.rows,BB::class.java,4)
                    rv_hot.layoutManager = GridLayoutManager(this@HomeFragment.requireActivity(),2)
                    rv_hot.adapter = adapter
                }
            }

        })
        ServiceCreate.smartcityService.getNewsClass().enqueue(object :Callback<NewsClass>{
            override fun onFailure(p0: Call<NewsClass>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<NewsClass>, p1: Response<NewsClass>) {
                val body = p1.body()
                if (body != null){
                    val adapter = PagerAdapter(this@HomeFragment.requireFragmentManager(),body.data)
                    v1.adapter = adapter
                    t1.setupWithViewPager(v1)
                }
            }

        })

    }


}

class AA(view: View):ItemAdapter.MyViewHolder(view){
    val name:TextView = view.findViewById(R.id.s_text)
    val iamge:ImageView = view.findViewById(R.id.s_image)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as HomeService.Row).serviceName
        Glide.with(iamge).load(ServiceCreate.url + (data[position] as HomeService.Row).imgUrl).into(iamge)

        if (position == 9){
            name.text = "更多服务"
            Glide.with(iamge).load(ServiceCreate.url + (data[position] as HomeService.Row).imgUrl).into(iamge)

            val aa = list[0] as MainActivity
            itemView.setOnClickListener {
                aa.bottom1.selectedItemId = R.id.nav_fun
            }

        }
    }
}

class BB(view: View):ItemAdapter.MyViewHolder(view){
    val name:TextView = view.findViewById(R.id.h_title)
    val iamge:ImageView = view.findViewById(R.id.h_image)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as NewsList.Row).title
        Glide.with(iamge).load(ServiceCreate.url + (data[position] as NewsList.Row).cover).into(iamge)
    }
}