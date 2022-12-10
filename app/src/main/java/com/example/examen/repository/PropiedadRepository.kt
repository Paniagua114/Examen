package com.example.examen.repository

import androidx.lifecycle.MutableLiveData
import com.example.examen.data.PropiedadDao
import com.example.examen.model.Propiedad

class PropiedadRepository(private val propiedadDao: PropiedadDao) {

    fun agregarPropiedad(propiedad: Propiedad) {
        propiedadDao.agregarPropiedad(propiedad)
    }

    fun eliminarPropiedad(propiedad: Propiedad) {
        propiedadDao.eliminarPropiedad(propiedad)
    }

    val obtenerPropiedad : MutableLiveData<List<Propiedad>> = propiedadDao.obtenerPropiedad()

}