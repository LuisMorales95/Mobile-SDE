package com.jlmp.mobilesdecodeexercise.presentation.driver

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jlmp.mobilesdecodeexercise.base.BaseFragment
import com.jlmp.mobilesdecodeexercise.R
import com.jlmp.mobilesdecodeexercise.presentation.adapter.DriverAdapter
import com.jlmp.mobilesdecodeexercise.databinding.FragmentDriverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DriverFragment : BaseFragment<FragmentDriverBinding>(R.layout.fragment_driver) {

    private val model: DriverViewModel by viewModels()
    private lateinit var adapter: DriverAdapter

    override fun initComponent() {
        initRecycler()
        model.drivers.observe(this) {
            adapter.submitList(it)
        }
        model.dataLoading.observe(this) {
            binding.progress.visibility = visible(it)
        }
        model.getDrivers()
    }

    private fun visible(it: Boolean) = if (it) View.VISIBLE else View.GONE

    private fun initRecycler() {
        adapter = DriverAdapter(DriverAdapter.DriverListener {
            launch(
                DriverFragmentDirections.actionDriverFragmentToDriverDetailFragment(it)
            )
        })
        binding.recycler.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        binding.recycler.setHasFixedSize(false)
        binding.recycler.adapter = adapter
    }
}