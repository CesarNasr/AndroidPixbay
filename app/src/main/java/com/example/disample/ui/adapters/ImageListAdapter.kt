package com.example.disample.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.disample.data.model.image.Hit
import com.example.disample.databinding.ItemImageBinding


class ImageListAdapter : RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }
    }


    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner class ImageViewHolder(val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hit: Hit) {
            binding.createdByText.text = hit.user


            Glide.with(binding.imageView.context)
                .load(hit.largeImageURL)
                .into(binding.imageView)

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(hit)
                }
            }
        }
    }


    private var onItemClickListener: ((Hit) -> Unit)? = null

    fun setOnItemClickListener(listener: (Hit) -> Unit) {
        onItemClickListener = listener
    }


}

