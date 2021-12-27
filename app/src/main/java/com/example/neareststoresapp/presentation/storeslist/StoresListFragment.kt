package com.example.neareststoresapp.presentation.storeslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neareststoresapp.databinding.FragmentStoresListBinding
import com.example.neareststoresapp.presentation.base.BaseFragment
import com.example.neareststoresapp.presentation.storeslist.adapters.StoresListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoresListFragment : BaseFragment<FragmentStoresListBinding>(FragmentStoresListBinding::inflate) {

    private val storesListViewModel by viewModel<StoresListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = super.onCreateView(inflater, container, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = storesListViewModel

        storesListViewModel.getStores(longitude = 26.100034, latitude = 44.431718)
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

}