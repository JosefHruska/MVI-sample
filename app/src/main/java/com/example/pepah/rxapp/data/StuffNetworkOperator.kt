package com.example.pepah.rxapp.data

import com.example.pepah.rxapp.model.Stuff
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * TODO: Add description
 *
 * @author Josef Hruška (josef@stepuplabs.io)
 */
object StuffNetworkOperator {

    fun checkCredentials(): Observable<List<Stuff>> {
        return Observable.just(getRandomListOfStuff())
                .delay(10000, TimeUnit.MILLISECONDS)
    }

    private fun getRandomListOfStuff(): List<Stuff> {
        val random = Random()
        val list = mutableListOf<Stuff>()
        for (data in 1..random.nextInt(20)) {
            list.add(Stuff())
        }
        return list
    }

}