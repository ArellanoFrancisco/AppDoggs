package com.example.appdoggs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appdoggs.R
import com.example.appdoggs.databinding.FragmentFirstBinding
import com.example.appdoggs.view.Adapter.RazasAdapter
import com.example.appdoggs.viewModel.DogViewModel


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: DogViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RazasAdapter()
        binding.RvRazas.adapter = adapter
        binding.RvRazas.layoutManager = LinearLayoutManager(context)

        viewModel.getBreedList().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.update(it)
            }
        })

        adapter.selectedBreed().observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.getImagesByBreedFromInternet(it.breed)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        })
    }


}