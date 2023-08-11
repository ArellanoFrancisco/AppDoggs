package com.example.appdoggs.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appdoggs.R
import com.example.appdoggs.databinding.FragmentSecondBinding
import com.example.appdoggs.view.Adapter.ImagesAdapter
import com.example.appdoggs.viewModel.DogViewModel

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: DogViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ImagesAdapter()
        binding.RvImages.adapter = adapter
        binding.RvImages.layoutManager = GridLayoutManager(context ,1)

        viewModel.getImages().observe(viewLifecycleOwner) {
            it?.let {
                adapter.update(it)
            }
        }

        adapter.selectedImage().observe(viewLifecycleOwner) {
            it?.let {
                if(it.fav) {
                    it.fav = false
                    viewModel.updateFav(it)
                    Log.d("Fav", " no es fav")
                    Toast.makeText(context, R.string.NoesFav, Toast.LENGTH_LONG).show()
                } else {
                    it.fav = true
                    Log.d("Fav", " es fav")
                    viewModel.updateFav(it)
                    Toast.makeText(context, R.string.esFav, Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}