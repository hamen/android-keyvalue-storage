package com.ivanmorgillo.keyvaluestorage

import android.content.Context
import android.content.SharedPreferences

object KeyValueStorageFactory {
    fun build(context: Context, storeName: String): KeyValueStorage {
        val sp: SharedPreferences = context.getSharedPreferences(storeName, Context.MODE_PRIVATE)
        return KeyValueStorageSpImpl(sp, DefaultDispatcherProvider)
    }

    fun build(sharedPreferences: SharedPreferences): KeyValueStorage = KeyValueStorageSpImpl(sharedPreferences, DefaultDispatcherProvider)
}
