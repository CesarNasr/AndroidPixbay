package com.example.disample.utils

import android.text.TextUtils
import android.util.Patterns


class Validators {
    fun isEmailValid(emailStr: String): Boolean {
        return !TextUtils.isEmpty(emailStr) && Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        if (password.isEmpty())
            return false
        else if (password.length <  6 || password.length > 12)
            return false

        return true
    }
}