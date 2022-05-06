package com.jiyeon.soptseminar.util

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes layoutResId: Int,
    private val viewModelClass:Class<VM>
) :
    AppCompatActivity() {
    lateinit var binding: VB
    lateinit var viewModel: VM
    var layoutId = layoutResId


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = ViewModelProvider(this)[viewModelClass]

        initViewModel()
    }

    protected  open fun initViewModel(){}
}