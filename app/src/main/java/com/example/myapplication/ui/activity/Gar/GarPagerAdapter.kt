package com.example.myapplication.ui.activity.Gar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.network.NewsType

class GarPagerAdapter(fm:FragmentManager,val list: List<NewsType.Row>):FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return GarNewsListFragment.newInstance(list[position].id.toString(),"")
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].name
    }

}