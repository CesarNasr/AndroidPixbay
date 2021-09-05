package com.example.disample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.example.disample.R
import com.example.disample.data.model.APIResponse
import com.example.disample.data.model.User
import com.example.disample.data.repository.LoginRepository
import com.example.disample.network.utils.NetworkHelper
import com.example.disample.network.utils.Resource
import com.example.disample.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val loginRepository: LoginRepository
) : ViewModel() {
    lateinit var navController: NavController
    val loginUserResponse: MutableLiveData<Event<Resource<APIResponse<User>>>> = MutableLiveData()

    fun login(userName: String, password: String) {
        val user = User(userName, password)
        fetchUserLogin(user)
    }

    private fun fetchUserLogin(user: User) =
        viewModelScope.launch(Dispatchers.IO) {
            loginUserResponse.postValue(Event(Resource.Loading()))
            try {
                if (networkHelper.isNetworkAvailable()) { // Can be added as an interceptor at the level of retrofit alternatively

                    val response = loginRepository.loginUser(user)
                    loginUserResponse.postValue(Event(response))

                } else {
                    loginUserResponse.postValue(Event(Resource.Error("Internet is Not available")))
                }
            } catch (e: Exception) {
                loginUserResponse.postValue(Event(Resource.Error(e.message.toString())))
            }

        }


    fun navigateToRegisterFragment() {
        navController.navigate(R.id.action_loginFragment_to_registerFragment)
    }

    fun navigateSingleTop(action: NavDirections) {
        navController.navigate(action)
    }

}