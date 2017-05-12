package com.example.pepah.rxapp.ui

import com.example.pepah.rxapp.data.AwesomeNetworkModel
import cz.filipproch.reactor.base.translator.BaseReactorTranslator
import cz.filipproch.reactor.ui.events.ViewCreatedEvent
import io.reactivex.ObservableTransformer

/**
 * TODO: Add description
 *
 * @author Josef Hruška (josef@stepuplabs.io)
 */

class MainTranslator : BaseReactorTranslator() {

    override fun onCreated() {
        val fetchPostDetail = ObservableTransformer<ViewCreatedEvent, MainUiModel> {
            it.map { AwesomeNetworkModel.StuffRequest() }
                    .compose(AwesomeNetworkModel.fetchStuff)
                    .map {
                        when {
                            it.inProgress -> MainUiModel.Companion.LOADING
                            it.error != null -> MainUiModel.Companion.ERROR
                            it.stuffList != null -> MainUiModel.Companion.success("There is ${it.stuffList.size} stuff", "Thats a lot of stuff")
                            else -> MainUiModel.Companion.IDLE
                        }
                    }
                    .startWith(MainUiModel.Companion.IDLE)
        }

        translateToModel {
            it.ofType(ViewCreatedEvent::class.java)
                    .compose(fetchPostDetail)
        }
    }

}