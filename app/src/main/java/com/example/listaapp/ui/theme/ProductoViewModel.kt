package com.example.listaapp.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.listaapp.data.DatabaseProvider
import com.example.listaapp.data.ProductoRepository
import com.example.listaapp.data.Producto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProductoViewModel(application: Application) : AndroidViewModel(application) {

    private val db = DatabaseProvider.provideDatabase(application)
    private val productoDao = db.productoDao()
    private val repository = ProductoRepository(productoDao)

    val productos: Flow<List<Producto>> = repository.obtenerProductos()
    val productosComprados: Flow<List<Producto>> = repository.obtenerProductosComprados()


    fun insertar(producto: Producto) {
        viewModelScope.launch {
            repository.insertar(producto)
        }
    }

    fun eliminar(producto: Producto) {
        viewModelScope.launch {
            repository.eliminar(producto)
        }
    }

    fun marcarComoComprado(producto: Producto) {
        viewModelScope.launch {
            val actualizado = producto.copy(comprado = true)
            repository.actualizar(actualizado)
        }
    }

    fun actualizar(producto: Producto) {
        viewModelScope.launch {
            repository.actualizar(producto)
        }
    }

    fun obtenerProductoPorId(id: Int): Flow<Producto?> {
        return repository.obtenerProductoPorId(id)
    }
    fun volverALista(producto: Producto) {
        viewModelScope.launch {
            val actualizado = producto.copy(comprado = false)
            actualizar(actualizado)
        }
    }



}
