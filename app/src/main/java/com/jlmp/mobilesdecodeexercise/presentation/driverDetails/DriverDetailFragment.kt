package com.jlmp.mobilesdecodeexercise.presentation.driverDetails

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jlmp.mobilesdecodeexercise.R
import com.jlmp.mobilesdecodeexercise.base.BaseFragment
import com.jlmp.mobilesdecodeexercise.databinding.FragmentDriverDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DriverDetailFragment : BaseFragment<FragmentDriverDetailBinding>(R.layout.fragment_driver_detail) {

    private val driverDetailViewModel: DriverDetailViewModel by viewModels()

    private val args: DriverDetailFragmentArgs by navArgs()

    override fun initComponent() {
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.driverDetailViewModel = driverDetailViewModel
        Glide.with(binding.profileImage).load(R.drawable.ic_account_circle).into(binding.profileImage)
        driverDetailViewModel.getDriver(args.driverId)
    }
}