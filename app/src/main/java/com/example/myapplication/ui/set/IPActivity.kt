package com.example.myapplication.ui.set

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import com.example.myapplication.R
import com.example.myapplication.extensions.edit
import com.example.myapplication.extensions.sharedPreferences
import com.example.myapplication.extensions.showToast

class IPActivity(context: Context) : PopupWindow(context) {
   init {
       val view = LayoutInflater.from(context).inflate(R.layout.activity_i_p,null,false)
       val  ip = view.findViewById<EditText>(R.id.et_ip)
       val  dk = view.findViewById<EditText>(R.id.et_dk)
       val  Save = view.findViewById<Button>(R.id.save)

       this.apply {
           isClippingEnabled = true
           isFocusable = true
           width = ViewGroup.LayoutParams.MATCH_PARENT
           height = 700
           contentView = view
           setBackgroundDrawable(ColorDrawable(Color.WHITE))
       }

       Save.setOnClickListener {
           val ip = ip.text.toString()
           val dk = dk.text.toString()
           sharedPreferences.edit {
               putString("ip",ip)
               putString("dk",dk)
               "保存成功".showToast()
           }
       }
   }
}