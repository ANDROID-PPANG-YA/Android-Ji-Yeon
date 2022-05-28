package com.jiyeon.soptseminar.ui.navi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.databinding.ActivityNaviBinding

class NaviActivity : AppCompatActivity() {
    private lateinit var binding:ActivityNaviBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}