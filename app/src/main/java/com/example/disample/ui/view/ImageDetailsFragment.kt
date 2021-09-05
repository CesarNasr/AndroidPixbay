package com.example.disample.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.disample.R
import com.example.disample.databinding.FragmentHomeBinding
import com.example.disample.databinding.FragmentImageDetailsBinding
import com.example.disample.ui.viewmodel.ImageDetailsFragmentViewModel
import com.example.disample.ui.viewmodel.LoginViewModel

class ImageDetailsFragment : Fragment() {

    private val imageDetailsViewModel: ImageDetailsFragmentViewModel by viewModels()
    private lateinit var binding: FragmentImageDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageDetailsBinding.bind(view)
        binding.viewmodel = imageDetailsViewModel
        val args: ImageDetailsFragmentArgs by navArgs()
        imageDetailsViewModel.image = args.selectedImage
        Glide.with(binding.imageView.context)
            .load(imageDetailsViewModel.image.largeImageURL)
            .placeholder(R.drawable.ic_placeholder)
            .into(binding.imageView)
    }
}