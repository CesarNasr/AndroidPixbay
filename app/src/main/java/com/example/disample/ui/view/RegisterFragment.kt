package com.example.disample.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.disample.R
import com.example.disample.databinding.FragmentLoginBinding
import com.example.disample.databinding.FragmentRegisterBinding
import com.example.disample.network.utils.Resource
import com.example.disample.ui.viewmodel.LoginViewModel
import com.example.disample.ui.viewmodel.RegisterViewModel
import com.example.disample.utils.EventObserver
import com.example.disample.utils.Validators
import com.example.disample.utils.snack
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    val registerViewModel: RegisterViewModel by viewModels()

    @Inject
    lateinit var validators: Validators
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        binding.fragment = this
        binding.viewmodel = registerViewModel
        registerViewModel.navController = findNavController()

        observeResponse()
    }


    private fun observeResponse() {
        registerViewModel.registerUserResponse.observe(
            viewLifecycleOwner,
            EventObserver { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let {
                            registerViewModel.navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())

                        }
                    }
                    is Error -> {
                        binding.root.snack("Something went wrong")
                    }
                    is Resource.Loading -> {
                        // Show Loader
                    }
                    else -> {
                        binding.root.snack("Something went wrong")

                    }
                }
            })
    }


    fun register() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        val age = binding.agePicker.value

        if (!validators.isEmailValid(email)) {
            binding.emailEditText.error = "Email address invalid"
        } else if (!validators.isPasswordValid(password)) {
            binding.passwordEditText.error = "Password must be between 6 and 12 characters"
        } else {
            registerViewModel.register(email, password, age)
        }
    }
}