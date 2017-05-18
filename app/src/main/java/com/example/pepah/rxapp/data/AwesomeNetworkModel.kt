package com.example.pepah.rxapp.data

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * TODO: Add description
 *
 * @author Josef Hruška (josef@stepuplabs.io)
 */

object AwesomeNetworkModel {

    fun checkLogin(username: String, password: String): Observable<Boolean> {
        if (username == "pepa" && password == "password") {
           return Observable.just(true).delay(10000, TimeUnit.MILLISECONDS)
        }
        return Observable.just(false)
    }

}
