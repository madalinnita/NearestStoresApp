package com.example.neareststoresapp.presentation.storeslist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.Store
import com.example.core.model.Stores
import com.example.core.usecases.GetStoresUseCase
import com.example.core.utils.AppResult
import com.example.neareststoresapp.utils.ConnectivityUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StoresListViewModel(
    private val getStoresUserCase: GetStoresUseCase,
    private val context: Context
) : ViewModel() {

    private val _listOfStores = MutableLiveData<Stores>()
    val listOfStores: LiveData<Stores> = _listOfStores

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getStores(longitude: Double, latitude: Double) {
        if (ConnectivityUtils.isConnectionAvailable(context)) {
            viewModelScope.launch {
                _isLoading.value = true

                val result = withContext(Dispatchers.IO) {
                    getStoresUserCase.execute(longitude, latitude)
                }

                if (result is AppResult.Success) {
                    val stores = result.successData
                   if(stores.stores != null && stores.stores!!.isNotEmpty()) _listOfStores.value = stores else _error.value = "No stores found"
                } else if (result is AppResult.Error) {
                    _error.value = result.message ?: result.exception.message
                }
                _isLoading.value = false
            }
        }
    }
}