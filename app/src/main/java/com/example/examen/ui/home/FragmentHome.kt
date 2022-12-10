package com.example.examen.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen.R
import com.example.examen.adapter.PropiedadAdapter
import com.example.examen.databinding.FragmentHomeBinding
import com.example.examen.viewmodel.HomeViewModel

class FragmentHome : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.addPropiedadBT.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentHome_to_fragmentCrearPropiedad)
        }

        //Carga datos
        val propiedadAd = PropiedadAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = propiedadAd
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        homeViewModel.obtenerPropiedad.observe(viewLifecycleOwner){
                propiedades -> propiedadAd.setPropiedad(propiedades)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}