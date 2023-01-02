package com.jlmp.mobilesdecodeexercise.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding>(@LayoutRes val layout: Int) : Fragment() {

    lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent()
    }

    abstract fun initComponent()

    fun launch(destinationId: Int, bundle: Bundle? = null) {
        findNavController().navigate(destinationId, bundle)
    }
    fun launch(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    fun popBack(){
        findNavController().popBackStack()
    }
}