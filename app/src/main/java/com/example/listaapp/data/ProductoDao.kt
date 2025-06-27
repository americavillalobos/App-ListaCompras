package com.example.listaapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {
    @Insert
    suspend fun insertarProducto(producto: Producto)

    @Delete
    suspend fun eliminar(producto: Producto)

    /*@Query("SELECT * FROM productos")
    fun obtenerProductos(): Flow<List<Producto>>*/

    @Update
    suspend fun actualizarProducto(producto: Producto)

    @Query("SELECT * FROM productos WHERE comprado = 0")
    fun obtenerProductos(): Flow<List<Producto>>

    @Query("SELECT * FROM productos WHERE comprado = 1")
    fun obtenerHistorial(): Flow<List<Producto>>

    @Query("SELECT * FROM productos WHERE id = :id")
    fun obtenerProductoPorId(id: Int): Flow<Producto?>

    @Query("SELECT * FROM productos WHERE comprado = 1")
    fun obtenerProductosComprados(): Flow<List<Producto>>





}
