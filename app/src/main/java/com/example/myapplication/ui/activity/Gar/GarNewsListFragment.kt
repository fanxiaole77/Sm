package com.example.myapplication.ui.activity.Gar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.GarNewsList
import com.example.myapplication.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_hosp_tal.*
import kotlinx.android.synthetic.main.fragment_gar_news_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GarNewsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GarNewsListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gar_news_list, container, false)
    }


    fun dateStr2timeStamp(dateStr : String) : Date{
        val pattern  = "yyyy-MM-dd HH:mm:ss"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date = simpleDateFormat.parse(dateStr)
        return date
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ServiceCreate.smartcityService.GarNewsList(param1!!.toInt()).enqueue(object :Callback<GarNewsList>{
            override fun onFailure(p0: Call<GarNewsList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<GarNewsList>, p1: Response<GarNewsList>) {
               val body = p1.body()
                if (body != null){
                    val list = body.rows.sortedWith(compareBy {dateStr2timeStamp(it.createTime)})
                    val adapter = ItemAdapter(R.layout.item_gar_news_list,list,JJ::class.java)
                    gar_news_list.layoutManager = LinearLayoutManager(this@GarNewsListFragment.requireActivity())
                    gar_news_list.adapter = adapter
                }
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GarNewsListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GarNewsListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
class JJ(view: View):ItemAdapter.MyViewHolder(view){
    val title:TextView = view.findViewById(R.id.g_title)
    val time:TextView = view.findViewById(R.id.g_time)
    val iamge:ImageView = view.findViewById(R.id.g_image)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        title.text = (data[position] as GarNewsList.Row).title
        time.text = (data[position] as GarNewsList.Row).createTime
        Glide.with(iamge).load(ServiceCreate.url + (data[position] as GarNewsList.Row).imgUrl).into(iamge)
        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,GarContentActivity::class.java).apply {
                putExtra("gid",(data[position] as GarNewsList.Row).id)
            })
        }
    }
}