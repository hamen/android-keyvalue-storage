package com.ivanmorgillo.keyvaluestorage

interface KeyValueStorage {
    fun getAll(): Map<String, *>
    fun contains(key: String): Boolean
    fun remove(key: String): Boolean

    suspend fun clean() : Boolean

    suspend fun loadBoolean(key: String): Boolean?
    suspend fun storeBoolean(key: String, value: Boolean): Boolean

    suspend fun loadString(key: String): String?
    suspend fun storeString(key: String, value: String): Boolean

    suspend fun loadInt(key: String): Int?
    suspend fun storeInt(key: String, value: Int): Boolean
}
