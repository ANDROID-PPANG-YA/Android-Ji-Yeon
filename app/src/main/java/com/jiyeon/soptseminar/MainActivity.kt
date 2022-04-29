package com.jiyeon.soptseminar

import CameraFragment
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.jiyeon.soptseminar.databinding.ActivityMainBinding
import com.jiyeon.soptseminar.util.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>(R.layout.activity_main,MainViewModel::class.java) {


    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        initBottomNavi()
    }

    private fun initAdapter(){
        val fragmentList = listOf(ProfileFragment(),HomeFragment(),CameraFragment())

        homeViewPagerAdapter = HomeViewPagerAdapter(this)
        homeViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpMain.adapter = homeViewPagerAdapter
    }

    private fun initBottomNavi(){
        binding.vpMain.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bnvMain.menu.getItem(position).isChecked = false
            }
        })

        binding.bnvMain.setOnItemSelectedListener{
            when(it.itemId){
                R.id.menu_android -> {
                    binding.vpMain.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_list -> {
                    binding.vpMain.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpMain.currentItem = THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }

    }

    companion object{
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }

}