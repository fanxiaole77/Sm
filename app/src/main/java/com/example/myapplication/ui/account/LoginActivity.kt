package com.example.myapplication.ui.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.extensions.edit
import com.example.myapplication.extensions.sharedPreferences
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.Login
import com.example.myapplication.network.Message
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()
        go_register.setOnClickListener {
            startActivity(Intent(this,
                RegisterActivity::class.java))
            finish()
        }
        login.setOnClickListener {
            val user = et_user.text.toString()
            val pass = et_pass.text.toString()
            ServiceCreate.smartcityService.postLogin(Login(user,pass)).enqueue(object :Callback<Message>{
                override fun onFailure(p0: Call<Message>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                  val body = p1.body()
                    if (body!= null){
                        if (body.code == 200){
                            body.msg.showToast()
                            sharedPreferences.edit {
                                putString("token",body.token)
                            }
                            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        }else{
                            body.msg.showToast()
                        }
                    }
                }

            })
        }

    }
}