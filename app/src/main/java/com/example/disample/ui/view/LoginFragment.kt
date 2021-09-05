package com.example.disample.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.disample.R
import com.example.disample.databinding.FragmentLoginBinding
import com.example.disample.network.utils.Resource
import com.example.disample.ui.adapters.ImageListAdapter
import com.example.disample.ui.viewmodel.LoginViewModel
import com.example.disample.utils.EventObserver
import com.example.disample.utils.Validators
import com.example.disample.utils.snack
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var validators: Validators

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        binding.viewmodel = loginViewModel
        binding.fragment = this
        loginViewModel.navController = findNavController()
        observeResponse()
    }

    private fun observeResponse() {
        loginViewModel.loginUserResponse.observe(viewLifecycleOwner, EventObserver { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        loginViewModel
                            .navigateSingleTop(
                                LoginFragmentDirections
                                    .actionLoginFragmentToHomeFragment()
                            )
                    }
                }
                is Resource.Error -> {
                    binding.root.snack(getString(R.string.something_went_wrong))
                }

                is Resource.Loading -> {
                    // Place loader
                }
            }
        })
    }

    fun login() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        if (!validators.isEmailValid(email)) {
            binding.emailEditText.error = "Email address invalid"
        } else if (!validators.isPasswordValid(password)) {
            binding.passwordEditText.error = "Password must be between 6 and 12 characters"
        } else {
            loginViewModel.login(email, password)
        }
    }

}