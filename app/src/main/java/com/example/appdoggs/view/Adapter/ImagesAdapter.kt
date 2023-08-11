package com.example.appdoggs.view.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appdoggs.R
import com.example.appdoggs.databinding.ImageItemBinding
import com.example.appdoggs.model.local.Entities.DogsImages

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ImageDogVH>() {

    var listImages = listOf<DogsImages>()
    val selectedImage = MutableLiveData<DogsImages>()

    fun update(list: List<DogsImages>) {
        listImages = list
        notifyDataSetChanged()
    }

    fun selectedImage(): LiveData<DogsImages> = selectedImage

    inner class ImageDogVH(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {

        fun bind(image: DogsImages) {
            Glide.with(binding.root).load(image.imageUrl).into(binding.ivDog)
            if (image.fav) {
                binding.ivFab.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.baseline_favorite_24))
                binding.ivFab.setColorFilter(Color.RED)
                binding.ivFab.visibility = View.VISIBLE
            } else {
                binding.ivFab.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.twotone_favorite_24))
                binding.ivFab.setColorFilter(Color.GRAY)
                binding.ivFab.visibility = View.VISIBLE
            }
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            selectedImage.value = listImages[bindingAdapterPosition]
            return true
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDogVH {
        return ImageDogVH(ImageItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ImageDogVH, position: Int) {
        holder.bind(listImages[position])
    }

    override fun getItemCount() = listImages.size

}