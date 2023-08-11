package com.example.appdoggs.view.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.appdoggs.databinding.RazasItemBinding
import com.example.appdoggs.model.local.Entities.Razas

class RazasAdapter : RecyclerView.Adapter<RazasAdapter.RazasDogVH>() {

    private var listBreed = listOf<Razas>()
    private val selectedBreed = MutableLiveData<Razas>()

    fun update(list: List<Razas>) {
        listBreed = list
        notifyDataSetChanged()
    }

    fun selectedBreed(): LiveData<Razas> = selectedBreed

    inner class RazasDogVH(private val binding: RazasItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(razas: Razas) {
            binding.tvbreed.text = razas.breed
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedBreed.value = listBreed[bindingAdapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RazasDogVH {
        return RazasDogVH(RazasItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RazasDogVH, position: Int) {
        holder.bind(listBreed[position])
    }

    override fun getItemCount() = listBreed.size

}