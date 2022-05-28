package com.jiyeon.soptseminar.ui.navi

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.databinding.FragmentSample1Binding
import com.jiyeon.soptseminar.util.BaseFragment

class SampleFragment1:BaseFragment<FragmentSample1Binding>(R.layout.fragment_sample1) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_sampleFragment1_to_sampleFragment2)
        }
    }
}