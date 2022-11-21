package com.example.myapplication.ui.activity.hosp

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.Message
import com.example.myapplication.network.PostAdd
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Year
import java.util.*

class AddActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW
        k.setOnClickListener { finish() }
        k.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)

        bri.setOnClickListener {
            val linke = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val data = "${year}-${month+1}-${dayOfMonth}"
                a_riqi.setText(data)
            }
            val dialog = DatePickerDialog(this,linke,Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH)
            dialog.show()
        }
        var sex = "0"
        arg_sex.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.aman){
               sex = "0"
            }else{
                sex = "1"
            }
        }

        a_btn.setOnClickListener {
            val user = a_name.text.toString()
            val tel = a_phone.text.toString()
            val riqi = a_riqi.text.toString()
            val add = a_add.text.toString()
            val id = a_id.text.toString()
            Log.d("sex",sex)
          ServiceCreate.smartcityService.postAdd(PostAdd(add,riqi,id,user,sex,tel)).enqueue(object :Callback<Message>{
              override fun onFailure(p0: Call<Message>, p1: Throwable) {
              }

              override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                  val body =p1.body()
                  if (body != null){
                      if (body.code == 200){
                          "新只成功".showToast()
                      }else{
                          body.msg.showToast()
                      }
                  }
              }

          })
        }


    }
}