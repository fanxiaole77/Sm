package com.example.myapplication.ui.activity.hosp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.extensions.sharedPreferences
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.HospApi
import com.example.myapplication.network.Message
import com.example.myapplication.network.ServiceCreate
import com.example.myapplication.network.YY
import kotlinx.android.synthetic.main.activity_hosp_tal.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HospTalActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hosp_tal)

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW

        val name = sharedPreferences.getString("name","").toString()
        val id = intent.getIntExtra("acteid",0)
        val moeny = intent.getIntExtra("moeny",0)
        val type = intent.getStringExtra("type")
        val cname = intent.getStringExtra("cname")

        Log.d("name",name)
        Log.d("name",moeny.toString())

        tv_name.text = name
        tv_money.text = moeny.toString()
        tv_type.text = if (type == "1"){
            "专家号"
        }else{
            "普通号"
        }
        tv_cname.text = cname


        data.setOnClickListener {
            val likne = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val data = "${year}-${moeny+1}-${dayOfMonth}"
                tv_data.setText(data)
            }
            val dialog = DatePickerDialog(this,likne,Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH)
            dialog.show()
        }

        tiem.setOnClickListener {
            val likne = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                val time1 = "${hourOfDay}:${minute}"
                tv_time.setText(time1)
            }
            val dialog = TimePickerDialog(this,likne,Calendar.HOUR_OF_DAY,Calendar.MINUTE,true)
            dialog.show()
        }

        yy.setOnClickListener {




            val time11 = tv_time.text.toString()
            val data11 = tv_data.text.toString()
            val rq = "$data11 $time11"
            ServiceCreate.smartcityService.postHospApi(HospApi(id,moeny.toInt(),name,rq,type.toInt())).enqueue(object :Callback<Message>{
                override fun onFailure(p0: Call<Message>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                    val body = p1.body()
                    if (body != null){
                        if (body.code == 200){
                            startActivity(Intent(this@HospTalActivity,YYActivity::class.java))
                            finish()
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