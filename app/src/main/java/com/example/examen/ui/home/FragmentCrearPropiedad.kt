package com.example.examen.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.examen.R
import com.example.examen.databinding.FragmentCrearPropiedadBinding
import com.example.examen.model.Propiedad
import com.example.examen.viewmodel.HomeViewModel

class FragmentCrearPropiedad : Fragment() {
    private var _binding: FragmentCrearPropiedadBinding? = null
    private val binding get() = _binding!!
    private lateinit var PropiedadViewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        PropiedadViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentCrearPropiedadBinding.inflate(inflater, container, false)

        binding.addpropiedad.setOnClickListener { agregarPropiedad()
        }
        return binding.root
    }

    private fun agregarPropiedad() {
        val nombre = binding.etNombre.text.toString()
        val precio = binding.etPrecio.text.toString()
        val disponibilidad = binding.etDisp.text.toString()
        val ubicacion = binding.etUbi.text.toString()

        if (nombre.isNotEmpty()) {
            val propiedad = Propiedad("", nombre, precio, disponibilidad, ubicacion)
            PropiedadViewModel.agregarPropiedad(propiedad)
            Toast.makeText(requireContext(), "Exito", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_fragmentCrearPropiedad_to_fragmentHome)
        } else {  //No hay info del lugar...
            Toast.makeText(
                requireContext(), getString(R.string.msgMensaje),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}