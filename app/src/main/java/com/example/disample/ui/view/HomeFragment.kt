package com.example.disample.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disample.R
import com.example.disample.databinding.FragmentHomeBinding
import com.example.disample.network.utils.Resource
import com.example.disample.ui.adapters.ImageListAdapter
import com.example.disample.ui.viewmodel.HomeViewModel
import com.example.disample.utils.EventObserver
import com.example.disample.utils.snack
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var imagesAdapter: ImageListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.viewmodel = homeViewModel
        binding.fragment = this
        imagesAdapter.setOnItemClickListener {

            val bundle = Bundle().apply {
                putSerializable("selected_image", it)
            }

            findNavController().navigate(
                R.id.action_homeFragment_to_imageDetailsFragment,
                bundle
            )
        }
        observeResponse()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.imageRecyclerView.apply {
            adapter = imagesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


    private fun observeResponse() {
        homeViewModel.imagesResponse.observe(viewLifecycleOwner, EventObserver { response ->
            when (response) {
                is Resource.Success -> {
                    hideInternetError()
                    hideProgressBar()
                    response.data?.let {
                        imagesAdapter.differ.submitList(it.hits) // TODO FETCH ERROR WELL
                    }
                }
                is Resource.Error -> {
                    response.message?.let {
                        hideProgressBar()
                        showInternetError(it)
                    }

                }

                is Resource.Loading -> {
                    hideInternetError()
                    showProgressBar()
                }
            }
        })
    }


    private fun showInternetError(s: String) {
        binding.errorLayout.visibility = View.VISIBLE
        binding.root.snack(s)
    }

    private fun hideInternetError() {
        binding.errorLayout.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

}