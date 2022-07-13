package com.example.androidkotlinfinal.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlinfinal.database.entities.AccelerationData
import com.example.androidkotlinfinal.databinding.AccelelationDataItemBinding


class AccelerationListAdapter :
    ListAdapter<AccelerationData, AccelerationListAdapter.RawDataViewHolder>(DiffCallback()) {


    class RawDataViewHolder(private val binding: AccelelationDataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(accelerationData: AccelerationData) {
            binding.acceleration = accelerationData
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RawDataViewHolder {
        return RawDataViewHolder(
            AccelelationDataItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: RawDataViewHolder, position: Int) {
        val accelerationData = getItem(position)
        holder.bind(accelerationData)
    }
}

class DiffCallback : DiffUtil.ItemCallback<AccelerationData>() {
    override fun areItemsTheSame(oldItem: AccelerationData, newItem: AccelerationData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AccelerationData, newItem: AccelerationData): Boolean {
        return oldItem.x == newItem.x && oldItem.y == newItem.y && oldItem.z == newItem.z
    }
}

/**
 * clickListener là 1 function vậy thì làm sao inj ect cái funtion này
 * */
//class OnClickListener(private val clickListener: (User: User) -> Unit) {
//    fun onClick(user: User) = clickListener(user)
//}