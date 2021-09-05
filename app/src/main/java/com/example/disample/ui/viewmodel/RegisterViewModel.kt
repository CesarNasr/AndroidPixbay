package com.example.disample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.example.disample.data.model.APIResponse
import com.example.disample.data.model.User
import com.example.disample.data.repository.RegisterRepository
import com.example.disample.network.utils.NetworkHelper
import com.example.disample.network.utils.Resource
import com.example.disample.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val registerRepository: RegisterRepository
) : ViewModel() {
    val registerUserResponse: MutableLiveData<Event<Resource<APIResponse<User>>>> =
        MutableLiveData()

    lateinit var navController: NavController

    fun register(userName: String, password: String, age : Int) {
        val user = User(userName, password, age)
        fetchUserRegister(user)
    }

    private fun fetchUserRegister(user: User) =
        viewModelScope.launch(Dispatchers.IO) {
            registerUserResponse.postValue(Event(Resource.Loading()))
            try {

                if (networkHelper.isNetworkAvailable()) {
                    val response = registerRepository.registerUser(user)
                    registerUserResponse.postValue(Event(response))

                } else {
                    registerUserResponse.postValue(Event(Resource.Error("Internet is Not available")))
                }
            } catch (e: Exception) {
                registerUserResponse.postValue(Event(Resource.Error(e.message.toString())))
            }

        }

    fun navigate(action: NavDirections) {
        navController.popBackStack()
        navController.navigate(action)
    }

}

