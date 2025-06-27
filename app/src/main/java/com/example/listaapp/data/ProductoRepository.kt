package com.example.listaapp.data

import kotlinx.coroutines.flow.Flow

class ProductoRepository(private val productoDao: ProductoDao) {

    fun obtenerProductoPorId(id: Int): Flow<Producto?> {
        return productoDao.obtenerProductoPorId(id)
    }

    suspend fun insertar(producto: Producto) {
        productoDao.insertarProducto(producto)
    }

    suspend fun actualizar(producto: Producto) {
        productoDao.actualizarProducto(producto)
    }

    suspend fun eliminar(producto: Producto) {
        productoDao.eliminar(producto)
    }

    fun obtenerProductos(): Flow<List<Producto>> {
        return productoDao.obtenerProductos()
    }

    fun obtenerProductosComprados(): Flow<List<Producto>> {
        return productoDao.obtenerProductosComprados()
    }

}