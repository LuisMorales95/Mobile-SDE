package com.jlmp.mobilesdecodeexercise.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jlmp.domain.DriverWithShipment
import com.jlmp.mobilesdecodeexercise.R
import com.jlmp.mobilesdecodeexercise.databinding.ItemDriverBinding

class DriverAdapter(private val driverListener: DriverListener) : ListAdapter<DriverWithShipment, DriverAdapter.DriverHolder>(
    DriverDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverHolder {
        return DriverHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DriverHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, driverListener)
        }
    }

    class DriverHolder(private val binding: ItemDriverBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): DriverHolder {
                val inflater = LayoutInflater.from(parent.context)
                return DriverHolder(ItemDriverBinding.inflate(inflater, parent, false))
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(driverWithShipment: DriverWithShipment, driverListener: DriverListener) {
            Glide.with(binding.profileImage).load(R.drawable.ic_account_circle).into(binding.profileImage)
            binding.driverText.text = driverWithShipment.driver.name
            binding.addressText.text = driverWithShipment.shipment?.address ?: ""
            binding.root.setOnClickListener {
                if (driverWithShipment.shipment != null) {
                    driverListener.onClick( driverWithShipment.driver.id)
                }
            }
        }
    }
    class DriverDiffUtil : DiffUtil.ItemCallback<DriverWithShipment>() {
        override fun areItemsTheSame(oldItem: DriverWithShipment, newItem: DriverWithShipment): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DriverWithShipment, newItem: DriverWithShipment): Boolean {
            return oldItem.driver == newItem.driver && oldItem.shipment == newItem.shipment
        }
    }

    class DriverListener(private var click: (Long) -> Unit ) {
        fun onClick(driverId: Long) = click(driverId)
    }
}