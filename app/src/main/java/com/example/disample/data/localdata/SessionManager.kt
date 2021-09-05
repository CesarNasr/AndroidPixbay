package com.example.disample.data.localdata

import android.content.Context
import android.content.SharedPreferences
import com.example.disample.R
import com.example.disample.data.model.User

import com.google.gson.Gson

/**
 * Session manager to be used in case we had real user login / session tokens
 */
class SessionManager(context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        const val AUTH_TOKEN = "auth_token"
        const val REFRESH_TOKEN = "refresh_token"
        const val USER_DATA_STR = "user_str"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthData(
        authToken: String?,
        refreshToken: String?,
        user: User?,
    ) {
        val editor = prefs.edit()
        authToken?.let { editor.putString(AUTH_TOKEN, authToken) }
        refreshToken?.let { editor.putString(REFRESH_TOKEN, refreshToken) }
        editor.putString(USER_DATA_STR, gson.toJson(user))
        editor.apply()
    }

    fun clearAuthData() {
        val editor = prefs.edit()
        editor.clear().apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String {
        return prefs.getString(AUTH_TOKEN, "").toString()
    }

    fun fetchRefreshToken(): String? {
        return prefs.getString(REFRESH_TOKEN, null)
    }

    fun fetchUserData(): User? {
        val userStr = prefs.getString(USER_DATA_STR, null)
        return gson.fromJson(userStr, User::class.java)
    }
}