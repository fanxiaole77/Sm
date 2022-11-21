package com.example.myapplication.ui.activity.hosp

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
import com.example.myapplication.network.ServiceCreate
import com.example.myapplication.network.YY
import kotlinx.android.synthetic.main.activity_y_y.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class YYActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_y_y)
        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW
        x.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        x.setOnClickListener { finish() }

        ServiceCreate.smartcityService.getYY().enqueue(object :Callback<YY>{
            override fun onFailure(p0: Call<YY>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<YY>, p1: Response<YY>) {
               val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_yy,body.rows,II::class.java)
                    rv_yy.layoutManager = LinearLayoutManager(this@YYActivity)
                    rv_yy.adapter = adapter
                }
            }

        })
    }
}
class II(view: View):ItemAdapter.MyViewHolder(view){

    fun dateStr2timeStamp(dateStr : String?) : String {
        if (dateStr == null){
            return ""
        }
        val pattern  = "yyyy-MM-dd HH:mm"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date = simpleDateFormat.parse(dateStr)
        return SimpleDateFormat("yyyy年MM月dd日 E a HH时mm分").format(date)
    }

    val dingdan:TextView = view.findViewById(R.id.danhao)
    val jine:TextView = view.findViewById(R.id.jine)
    val jiuzhenren:TextView = view.findViewById(R.id.jiuzhen)
    val keshi:TextView = view.findViewById(R.id.keshi)
    val leixing:TextView = view.findViewById(R.id.leixing)
    val shijian:TextView = view.findViewById(R.id.shijian)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        dingdan.text = (data[position] as YY.Row).orderNo
        jine.text = (data[position] as YY.Row).money.toString()
        jiuzhenren.text = (data[position] as YY.Row).patientName
        keshi.text = (data[position] as YY.Row).categoryName
        leixing.text = if ((data[position] as YY.Row).type == "1"){
            "专家号"
        }else{
            "普通号"
        }
        shijian.text = dateStr2timeStamp((data[position] as YY.Row).reserveTime)
    }
}