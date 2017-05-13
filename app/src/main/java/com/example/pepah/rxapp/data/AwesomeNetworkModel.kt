package com.example.pepah.rxapp.data

/**
 * TODO: Add description
 *
 * @author Josef Hruška (josef@stepuplabs.io)
 */

object AwesomeNetworkModel {

    fun checkLogin(username: String, password: String): Boolean {
        if (username == "pepa" && password == "password") {
            Thread.sleep(5000) // It's difficult, you know.
            return true
        }
        return false
    }

}
