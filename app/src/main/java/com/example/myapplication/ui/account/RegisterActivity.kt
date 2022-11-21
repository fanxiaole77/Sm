package com.example.myapplication.ui.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.Message
import com.example.myapplication.network.Register
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
        go_login.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
        var sex = "0"
        rg_sex.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.man){
                sex = "1"
            }else{
                sex = "0"
            }
        }
        register.setOnClickListener {
            val user = et_user1.text.toString()
            val pass = et_pass1.text.toString()
            val nick = et_nick.text.toString()
            val phone = et_phone1.text.toString()
            val id = et_id1.text.toString()

            ServiceCreate.smartcityService.postRegister(Register(user,pass,nick,phone,id,sex)).enqueue(object :Callback<Message>{
                override fun onFailure(p0: Call<Message>, p1: Throwable) {

                }

                override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                    val body = p1.body()
                    if (body != null){
                        if (body.code == 200){
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