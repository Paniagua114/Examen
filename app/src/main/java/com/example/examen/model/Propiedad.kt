package com.example.examen.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Propiedad (
    var id_propiedad: String,
    val nombre: String,
    val precio: String,
    val disponibilidad: String,
    val ubicacion: String,
) : Parcelable {
    constructor():
            this("", "", "","", "")
}