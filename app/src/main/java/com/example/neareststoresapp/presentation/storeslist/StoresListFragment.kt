package com.example.neareststoresapp.presentation.storeslist

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neareststoresapp.databinding.FragmentStoresListBinding
import com.example.neareststoresapp.presentation.base.BaseFragment
import com.example.neareststoresapp.presentation.storeslist.adapters.StoresListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService
import android.app.Activity
import androidx.core.content.ContextCompat.getSystemService

class StoresListFragment : BaseFragment<FragmentStoresListBinding>(FragmentStoresListBinding::inflate) {

    private val storesListViewModel by viewModel<StoresListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = super.onCreateView(inflater, container, savedInstanceState)

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                101
            )
        } else {
            val lm = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if(location?.longitude != null && location?.latitude != null) storesListViewModel.getStores(longitude = location.longitude, latitude = location.latitude)

        }


        binding.lifecycleOwner = this
        binding.viewModel = storesListViewModel

        observeViewModel()

        val albumAdapter = StoresListAdapter(requireContext())
        binding.adapter = albumAdapter

        return root
    }

    private fun observeViewModel(){
        storesListViewModel.isLoading.observe(viewLifecycleOwner, {
            if(it) {
                binding.progressBarAlbums.visibility = View.VISIBLE
            } else {
                binding.progressBarAlbums.visibility = View.GONE
            }
        })

        storesListViewModel.error.observe(viewLifecycleOwner, {
            showPopup(message = it)
        })

        storesListViewModel.listOfStores.observe(viewLifecycleOwner, {
            (binding.adapter as StoresListAdapter).submitList(it.stores)
        })
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 101 && isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
            val lm = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if(location?.longitude != null && location?.latitude != null) storesListViewModel.getStores(longitude = location.longitude, latitude = location.latitude)
        }
    }

    private fun isPermissionGranted(grantPermission: Array<out String>, grantResults: IntArray, permission: String): Boolean {
        grantPermission.forEachIndexed {index, perm ->
            if(permission == perm) {
                return grantResults[index] == PackageManager.PERMISSION_GRANTED
            }
        }
        return false
    }

}