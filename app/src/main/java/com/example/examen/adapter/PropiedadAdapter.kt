package com.example.examen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.examen.FragmentCardView
import com.example.examen.databinding.FragmentCardViewBinding
import com.example.examen.model.Propiedad
import com.example.examen.ui.home.FragmentHomeDirections

class PropiedadAdapter : RecyclerView.Adapter<PropiedadAdapter.PropiedadViewHolder>(){

    private var listaPropiedades = emptyList<Propiedad>()
    inner class PropiedadViewHolder(private val itemBinding: FragmentCardViewBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun dibujar(propiedad: Propiedad){
            itemBinding.tvNombre.text = propiedad.nombre
            itemBinding.tvPrecio.text = propiedad.precio.toString()
            itemBinding.tvDisp.text = propiedad.disponibilidad.toString()
            itemBinding.tvUbi.text = propiedad.ubicacion

            //modificar

            itemBinding.vistaFila.setOnClickListener{
                val accion = FragmentHomeDirections
                    .actionFragmentHomeToFragmentActualizarPropiedad(propiedad)
                itemView.findNavController().navigate(accion)
            }
        }
    }

    fun setPropiedad(propiedad : List<Propiedad>){
        listaPropiedades = propiedad
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropiedadViewHolder {
        val itemBinding = FragmentCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PropiedadViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: PropiedadViewHolder, position: Int) {
        val propiedad = listaPropiedades[position]
        holder.dibujar(propiedad)
    }

    override fun getItemCount(): Int {
        return listaPropiedades.size
    }
}