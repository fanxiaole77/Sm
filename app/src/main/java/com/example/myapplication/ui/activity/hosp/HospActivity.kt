package com.example.myapplication.ui.activity.hosp

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.HospList
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_hosp.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hosp)

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW

        g.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        g.setOnClickListener { finish() }

        ServiceCreate.smartcityService.getHospList(null).enqueue(object :Callback<HospList>{
            override fun onFailure(p0: Call<HospList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HospList>, p1: Response<HospList>) {
                val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_hosp,body.rows,EE::class.java)
                    rv_hosp.layoutManager = LinearLayoutManager(this@HospActivity)
                    rv_hosp.adapter =adapter
                }
            }

        })

        h_sear.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                ServiceCreate.smartcityService.getHospList(newText).enqueue(object :Callback<HospList>{
                    override fun onFailure(p0: Call<HospList>, p1: Throwable) {
                    }

                    override fun onResponse(p0: Call<HospList>, p1: Response<HospList>) {
                        val body = p1.body()
                        if (body != null){
                            val adapter = ItemAdapter(R.layout.item_hosp,body.rows,EE::class.java)
                            rv_hosp.layoutManager = LinearLayoutManager(this@HospActivity)
                            rv_hosp.adapter =adapter
                        }
                    }

                })
                return false
            }
        })


    }
}
class EE(view: View):ItemAdapter.MyViewHolder(view){
    val name:TextView = view.findViewById(R.id.o_name)
    val image:ImageView = view.findViewById(R.id.o_image)
    val xingxing:RatingBar = view.findViewById(R.id.o_xingxing)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as HospList.Row).hospitalName
        Glide.with(image).load(ServiceCreate.url + (data[position] as HospList.Row).imgUrl).into(image)
        xingxing.rating = (data[position] as HospList.Row).level.toFloat()

        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,HospContentActivity::class.java).apply {
                putExtra("hospid",(data[position] as HospList.Row).id)
            })
        }
    }
}