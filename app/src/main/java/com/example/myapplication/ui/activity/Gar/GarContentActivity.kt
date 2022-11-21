package com.example.myapplication.ui.activity.Gar

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.*
import kotlinx.android.synthetic.main.activity_gar_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GarContentActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gar_content)

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW
        v.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        v.setOnClickListener { finish() }

        val id = intent.getIntExtra("gid",0)


        ServiceCreate.smartcityService.getGarNewsContent(id).enqueue(object :Callback<GarNewsContent>{
            override fun onFailure(p0: Call<GarNewsContent>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<GarNewsContent>, p1: Response<GarNewsContent>) {
               val body = p1.body()
                if (body != null){
                    tv_g_title.text = body.data.title
                    tv_g_time.text = body.data.createTime
                    tv_g_name.text = body.data.author
                    tv_g_content.text = Html.fromHtml(body.data.content)
                }
            }
        })
        fun aa() = ServiceCreate.smartcityService.getGarComment(id).enqueue(object :Callback<GarComment>{
            override fun onFailure(p0: Call<GarComment>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<GarComment>, p1: Response<GarComment>) {
                val body =p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_g_comment,body.rows,KK::class.java)
                    rv_g_comment.layoutManager = LinearLayoutManager(this@GarContentActivity)
                    rv_g_comment.adapter = adapter
                }
            }

        })
        aa()
        btn_g_comment.setOnClickListener {
            val comment = et_comment.text.toString()
            ServiceCreate.smartcityService.postComment(ReleaseComment(comment,id)).enqueue(object :Callback<Message>{
                override fun onFailure(p0: Call<Message>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                   val body = p1.body()
                    if (body != null){
                        if (body.code == 200){
                            aa()
                            body.msg.showToast()
                        }else{
                            body.msg.showToast()
                        }
                    }
                }

            })
        }

    }
}
class KK(view: View):ItemAdapter.MyViewHolder(view){
    val name:TextView = view.findViewById(R.id.tv_g_user)
    val comment:TextView = view.findViewById(R.id.tv_g_content)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as GarComment.Row).userId.toString()
        comment.text = (data[position] as GarComment.Row).content
    }
}