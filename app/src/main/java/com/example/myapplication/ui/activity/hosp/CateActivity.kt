package com.example.myapplication.ui.activity.hosp

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.Cate
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_cate.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CateActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cate)

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW

        l.setOnClickListener { finish() }
        l.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)

      btn1.setOnClickListener {
          ServiceCreate.smartcityService.getCate("1").enqueue(object :Callback<Cate>{
              override fun onFailure(p0: Call<Cate>, p1: Throwable) {
              }

              override fun onResponse(p0: Call<Cate>, p1: Response<Cate>) {
                 val body = p1.body()
                  if (body != null){
                      val adapter = ItemAdapter(R.layout.item_cate,body.rows,GG::class.java)
                      rv_cate.layoutManager = LinearLayoutManager(this@CateActivity)
                      rv_cate.adapter = adapter
                  }
              }

            })
        }
        btn2.setOnClickListener {
            ServiceCreate.smartcityService.getCate("2").enqueue(object :Callback<Cate>{
                override fun onFailure(p0: Call<Cate>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<Cate>, p1: Response<Cate>) {
                    val body = p1.body()
                    if (body != null){
                        val adapter = ItemAdapter(R.layout.item_cate,body.rows,GG::class.java)
                        rv_cate.layoutManager = LinearLayoutManager(this@CateActivity)
                        rv_cate.adapter = adapter
                    }
                }

            })
        }

    }
}
class GG(view: View):ItemAdapter.MyViewHolder(view){
    val text:TextView = view.findViewById(R.id.care)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        text.text = (data[position] as Cate.Row).categoryName
        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,HospTalActivity::class.java).apply {
                putExtra("moeny",(data[position] as Cate.Row).money.toInt())
                putExtra("acteid",(data[position] as Cate.Row).id)
                putExtra("cname",(data[position] as Cate.Row).categoryName)
                putExtra("type",(data[position] as Cate.Row).type)
            })
        }
    }

}