package com.example.examen.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.examen.R
import com.example.examen.databinding.FragmentActualizarPropiedadBinding
import com.example.examen.model.Propiedad
import com.example.examen.viewmodel.HomeViewModel

class FragmentActualizarPropiedad : Fragment() {
    private val args by navArgs<FragmentActualizarPropiedadArgs>()
    private var _binding : FragmentActualizarPropiedadBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentActualizarPropiedadBinding.inflate(inflater, container, false)

        binding.etNombre.setText(args.propiedad.nombre)
        binding.etPrecio.setText(args.propiedad.precio).toString()
        binding.etDisp.setText(args.propiedad.disponibilidad)
        binding.etUbi.setText(args.propiedad.ubicacion)

        binding.updatePropiedad.setOnClickListener{
            actualizarPropiedad()
        }
        binding.eliminarPropiedad.setOnClickListener{
            eliminarPropiedad()
        }
        return binding.root
    }

    private fun actualizarPropiedad(){
        val nombre = binding.etNombre.text.toString()
        val precio = Integer.parseInt(binding.etPrecio.text.toString())
        val disponibilidad = binding.etDisp.text.toString()
        val ubicacion = binding.etUbi.text.toString()

        if (nombre.isEmpty()){
            Toast.makeText(requireContext(), getString(R.string.msg_data), Toast.LENGTH_LONG)

        } else if (ubicacion.isEmpty()){
            Toast.makeText(requireContext(), getString(R.string.msg_data), Toast.LENGTH_LONG)
        }
        else{
            val propiedad = Propiedad(args.propiedad.id_propiedad, nombre, precio, disponibilidad, ubicacion)
            homeViewModel.agregarPropiedad(propiedad)
            Toast.makeText(requireContext(), getString(R.string.msgMensajeAct), Toast.LENGTH_LONG)
            findNavController().navigate(R.id.action_fragmentActualizarPropiedad_to_fragmentHome)
        }
    }

    private fun eliminarPropiedad(){
        val nombre = binding.etNombre.text.toString()
        val precio = Integer.parseInt(binding.etPrecio.text.toString())
        val disponibilidad = binding.etDisp.text.toString()
        val ubicacion = binding.etUbi.text.toString()

        if (nombre.isEmpty()){
            Toast.makeText(requireContext(), getString(R.string.msg_data), Toast.LENGTH_LONG)

        } else if (ubicacion.isEmpty()){
            Toast.makeText(requireContext(), getString(R.string.msg_data), Toast.LENGTH_LONG)
        }
        else{
            val propiedad = Propiedad(args.propiedad.id_propiedad, nombre, precio, disponibilidad, ubicacion)
            homeViewModel.eliminarPropiedad(propiedad)
            Toast.makeText(requireContext(), getString(R.string.msgMensajeEli), Toast.LENGTH_LONG)
            findNavController().navigate(R.id.action_fragmentActualizarPropiedad_to_fragmentHome)
        }
    }
}