package com.jiyeon.soptseminar.util

import android.content.Context
import android.content.SharedPreferences

object SOPTSharedPreferences {
    private const val STORAGE_KEY = "USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
    }

    fun getAutoLogin(context: Context): Boolean {
        return preferences.getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(value: Boolean) {
        preferences.edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }

    fun setLogout(context: Context) {
        preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        preferences.edit()
            .remove(AUTO_LOGIN)
            .clear()
            .apply()
    }
}