package com.example.pepah.rxapp.data

import android.util.Log
import com.example.pepah.rxapp.model.Stuff
import io.reactivex.ObservableTransformer

/**
 * TODO: Add description
 *
 * @author Josef Hruška (josef@stepuplabs.io)
 */

object AwesomeNetworkModel {

    val fetchStuff = ObservableTransformer<StuffRequest, StuffFetchResult> {
        it
                .doOnNext { Log.d("DEBUG", "$it") }
                .flatMap {
                    StuffNetworkOperator.checkCredentials()
                            .doOnNext { Log.v("DEBUG", "$it") }
                            .map { StuffFetchResult.success(it) }
                            .onErrorReturn { StuffFetchResult.failure(it) }
                            .startWith(StuffFetchResult.inProgress())
                }
    }

    class StuffRequest

    class StuffFetchResult(val stuffList: List<Stuff>?, val error: Throwable?, val inProgress: Boolean) {
        companion object {
            fun success(stuffList: List<Stuff>): StuffFetchResult {
                return StuffFetchResult(stuffList, null, false)
            }

            fun failure(error: Throwable): StuffFetchResult {
                return StuffFetchResult(null, error, false)
            }

            fun inProgress(): StuffFetchResult {
                return StuffFetchResult(null, null, true)
            }
        }
    }

}
