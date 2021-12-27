package com.example.neareststoresapp.presentation.storeslist.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.model.Store
import com.example.neareststoresapp.databinding.StoreItemBinding

class StoresListAdapter(val context: Context) :
    ListAdapter<Store, StoresListAdapter.AlbumHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<Store>() {
        override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean =
            oldItem.posId == newItem.posId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = StoreItemBinding.inflate(layoutInflater, parent, false)

        return AlbumHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        val store = getItem(position)
        holder.binding.apply {
            this.store = store
            executePendingBindings()
        }
        holder.binding.parentLayoutCons.setOnClickListener {
            val lat = store.latitude
            val lng = store.longitude
            val mTitle = "My Assessment"
            val geoUri = "http://maps.google.com/maps?q=loc:$lat,$lng ($mTitle)"

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            context.applicationContext.startActivity(intent)
        }
    }

    class AlbumHolder(val binding: StoreItemBinding) : RecyclerView.ViewHolder(binding.root)
}