package com.example.myapplication.ui.`fun`

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.HomeService
import com.example.myapplication.network.ServiceCreate
import com.example.myapplication.ui.activity.Gar.GarActivity
import com.example.myapplication.ui.activity.hosp.HospActivity
import kotlinx.android.synthetic.main.fragment_fun.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FunFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fun, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ServiceCreate.smartcityService.getHomeService().enqueue(object :Callback<HomeService>{
            override fun onFailure(p0: Call<HomeService>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HomeService>, p1: Response<HomeService>) {
               val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_service,body.rows,DD::class.java)
                    rv_fun.layoutManager = GridLayoutManager(this@FunFragment.requireActivity(),5)
                    rv_fun.adapter = adapter
                }
            }

        })

    }

}
class DD(view: View): ItemAdapter.MyViewHolder(view){
    val name: TextView = view.findViewById(R.id.s_text)
    val iamge: ImageView = view.findViewById(R.id.s_image)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as HomeService.Row).serviceName
        Glide.with(iamge).load(ServiceCreate.url + (data[position] as HomeService.Row).imgUrl).into(iamge)

        when((data[position] as HomeService.Row).serviceName){
            "门诊预约" -> {
                itemView.setOnClickListener {
                    name.context.startActivity(Intent(name.context,HospActivity::class.java))
                }
            }

            "垃圾分类" -> {
                itemView.setOnClickListener {
                    name.context.startActivity(Intent(name.context,GarActivity::class.java))
                }
            }
        }

    }
}