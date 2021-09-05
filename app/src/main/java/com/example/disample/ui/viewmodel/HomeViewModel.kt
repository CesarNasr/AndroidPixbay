package com.example.disample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disample.data.model.image.PixbayEntryPoint
import com.example.disample.data.repository.HomeRepository
import com.example.disample.network.utils.NetworkHelper
import com.example.disample.network.utils.Resource
import com.example.disample.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val networkHelper: NetworkHelper,
) : ViewModel() {
    val imagesResponse: MutableLiveData<Event<Resource<PixbayEntryPoint>>> = MutableLiveData()

    init {
        fetchImages()
    }

     fun fetchImages() =
        viewModelScope.launch(Dispatchers.IO) {
            imagesResponse.postValue(Event(Resource.Loading()))
            try {
                if (networkHelper.isNetworkAvailable()) {
                    val response = homeRepository.fetchImages()
                    imagesResponse.postValue(Event(response))
                } else {
                    imagesResponse.postValue(Event(Resource.Error("Internet is Not available")))
                }
            } catch (e: Exception) {
                imagesResponse.postValue(Event(Resource.Error(e.message.toString())))
            }
        }

}