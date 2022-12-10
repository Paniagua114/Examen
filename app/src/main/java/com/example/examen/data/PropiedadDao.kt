package com.example.examen.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.examen.model.Propiedad
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase

class PropiedadDao {
    private var codigoUsuario: String
    private var firestore: FirebaseFirestore

    init{
        val usuario = Firebase.auth.currentUser?.email
        codigoUsuario = "$usuario"
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    //Insertar
    fun agregarPropiedad(propiedad: Propiedad){
        val document: DocumentReference

        if (propiedad.id_propiedad.isEmpty()){
            document = firestore.
            collection("Examen").
            document(codigoUsuario).
            collection("misPropiedades").
            document()
            propiedad.id_propiedad = document.id


        } else {
            //modificar
            document = firestore.collection("Examen").
            document(codigoUsuario).
            collection("misPropiedades").document(propiedad.id_propiedad)
            propiedad.id_propiedad = document.id
        }
        document.set(propiedad).addOnCompleteListener{
            Log.d("Agregar Propiedad", "Guardado exitosamente")
        }
            .addOnCanceledListener { Log.e("Agregar Propiedad", "Error al guardar") }
    }

    //Delete
    fun eliminarPropiedad(propiedad: Propiedad){

        if(propiedad.id_propiedad.isNotEmpty()){
            firestore.collection("Examen").document(codigoUsuario).
            collection("misPropiedades").
            document(propiedad.id_propiedad).delete()

                .addOnCompleteListener{
                    Log.d("Eliminar propiedad", "Eliminado exitosamente")
                }
                .addOnCanceledListener { Log.e("Eliminar propiedad", "Error al eliminar") }
        }
    }


    fun obtenerPropiedad(): MutableLiveData<List<Propiedad>> {
        val listaPropiedad = MutableLiveData<List<Propiedad>>()

        firestore
            .collection("Examen").
            document(codigoUsuario).
            collection("misPropiedades").addSnapshotListener { snapshot, e->
                if (e != null){
                    return@addSnapshotListener
                }
                if(snapshot != null) {
                    val lista = ArrayList<Propiedad>()
                    val propiedades = snapshot.documents
                    propiedades.forEach {
                        val propiedad = it.toObject(Propiedad::class.java)
                        if (propiedad != null) {
                            lista.add(propiedad)
                        }
                    }
                    listaPropiedad.value = lista
                }
            }
        return listaPropiedad
    }
}
