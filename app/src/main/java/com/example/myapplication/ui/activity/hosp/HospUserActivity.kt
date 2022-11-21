package com.example.myapplication.ui.activity.hosp

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.extensions.edit
import com.example.myapplication.extensions.sharedPreferences
import com.example.myapplication.network.HospUser
import com.example.myapplication.network.ServiceCreate
import com.example.myapplication.network.UserInfo
import kotlinx.android.synthetic.main.activity_hosp_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospUserActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hosp_user)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onResume() {
        super.onResume()

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW

        j.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        j.setOnClickListener { finish() }

        var sex = "0"

        ServiceCreate.smartcityService.getUserInfo().enqueue(object : Callback<UserInfo> {
            override fun onFailure(p0: Call<UserInfo>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<UserInfo>, p1: Response<UserInfo>) {
                val body = p1.body()
                if (body != null) {
                    h_id.text = body.user.idCard
                    h_sex.text = if (body.user.sex == "0") {
                        Log.d("sex", "${body.user.sex}")
                        "男"
                    } else {
                        "女"
                    }
                    h_name.text = body.user.userName
                    h_phone.text = body.user.phonenumber
                }
            }

        })

        ServiceCreate.smartcityService.getHospUSer().enqueue(object : Callback<HospUser> {
            override fun onFailure(p0: Call<HospUser>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HospUser>, p1: Response<HospUser>) {
                val body = p1.body()
                if (body != null) {
                    val adapter = ItemAdapter(R.layout.item_hosp_user, body.rows, FF::class.java)
                    rv_hosp_user.layoutManager = LinearLayoutManager(this@HospUserActivity)
                    rv_hosp_user.adapter = adapter
                }
            }

        })

        btn_add.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        add.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        add_add.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

    }
}

class FF(view: View) : ItemAdapter.MyViewHolder(view) {
    val name: TextView = view.findViewById(R.id.hh_name)
    val add: TextView = view.findViewById(R.id.hh_add)
    val riqi: TextView = view.findViewById(R.id.hh_riqi)
    val cid: TextView = view.findViewById(R.id.hh_id)
    val sex: TextView = view.findViewById(R.id.hh_sex)
    val tel: TextView = view.findViewById(R.id.hh_phone)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as HospUser.Row).name
        add.text = (data[position] as HospUser.Row).address
        riqi.text = (data[position] as HospUser.Row).birthday
        cid.text = (data[position] as HospUser.Row).cardId
        sex.text = if ((data[position] as HospUser.Row).sex == "0") {
            "男"
        } else {
            "女"
        }
        tel.text = (data[position] as HospUser.Row).tel

        itemView.setOnClickListener {
            itemView.context.startActivity(
                Intent(itemView.context,CateActivity::class.java).apply {
                    sharedPreferences.edit {
                        putString("name", (data[position] as HospUser.Row).name)
                    }
                })
            Log.d("name",(data[position] as HospUser.Row).name)
        }
    }

}