package com.example.examen.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.examen.data.PropiedadDao
import com.example.examen.model.Propiedad
import com.example.examen.repository.PropiedadRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val obtenerPropiedad : MutableLiveData<List<Propiedad>>
    private val repository: PropiedadRepository

    init {
        repository = PropiedadRepository(PropiedadDao())
        obtenerPropiedad = repository.obtenerPropiedad
    }

    fun agregarPropiedad(propiedad: Propiedad) {
        repository.agregarPropiedad(propiedad)
    }

    fun eliminarPropiedad(propiedad: Propiedad) {
        repository.eliminarPropiedad(propiedad)
    }


}