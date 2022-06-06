package com.wednesday.test.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import com.wednesday.test.databinding.ItemCatsBinding
import com.wednesday.test.domain.model.Cat

class CatAdapter : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Cat>(){
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CatViewHolder {
        return CatViewHolder(
            ItemCatsBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(
        holder: CatViewHolder, position: Int
    ) = holder.bind(differ.currentList[position])

    inner class CatViewHolder(
        val binding: ItemCatsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        //Bind the item position data to the ViewHolder
        fun bind(cat: Cat) {
            //Use Coil for Image Loading from Network Request
            val imageLoader = binding.catImage.context.imageLoader
            val request = ImageRequest.Builder(binding.catImage.context)
                .data(cat.url)
                .listener(
                    //Show ImageLoader on trying to load image
                    onStart = { binding.imageProgress.visibility = View.VISIBLE },
                    //Hide Loader if Image loading completes or fails
                    onSuccess = { _, _ -> binding.imageProgress.visibility = View.GONE },
                    onError = { _, _ -> binding.imageProgress.visibility = View.GONE },
                ).target(binding.catImage)
                .build()
            //Make the request to the image url
            imageLoader.enqueue(request)
        }
    }
}