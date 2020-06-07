package com.ivanmorgillo.keyvaluestorage

import android.content.SharedPreferences
import kotlinx.coroutines.withContext

@Suppress("TooManyFunctions")
class KeyValueStorageSpImpl(
    private val storage: SharedPreferences,
    private val dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider
) : KeyValueStorage {

    override fun getAll(): Map<String, *> = storage.all

    override fun contains(key: String) = storage.contains(key)

    override fun remove(key: String) = storage.edit().remove(key).commit()

    override suspend fun clean(): Boolean = withContext(dispatcherProvider.io()) {
        storage.edit().clear().commit()
    }

    override suspend fun storeBoolean(key: String, value: Boolean) = withContext(dispatcherProvider.io()) {
        storage.edit().putBoolean(key, value).commit()
    }

    override suspend fun loadBoolean(key: String): Boolean? = withContext(dispatcherProvider.io()) {
        try {
            if (storage.contains(key)) storage.getBoolean(key, false)
            else null
        } catch (cce: ClassCastException) {
            null
        }
    }

    override suspend fun loadString(key: String): String? = withContext(dispatcherProvider.io()) {
        try {
            storage.getString(key, null)
        } catch (cce: ClassCastException) {
            null
        }
    }

    override suspend fun storeString(key: String, value: String): Boolean = withContext(dispatcherProvider.io()) {
        storage.edit().putString(key, value).commit()
    }

    override suspend fun loadInt(key: String): Int? = withContext(dispatcherProvider.io()) {
        try {
            if (storage.contains(key)) storage.getInt(key, -1)
            else null
        } catch (cce: ClassCastException) {
            null
        }
    }

    override suspend fun storeInt(key: String, value: Int): Boolean = withContext(dispatcherProvider.io()) {
        storage.edit().putInt(key, value).commit()
    }
}
