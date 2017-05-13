package com.example.pepah.rxapp.presenter

import com.example.pepah.rxapp.data.AwesomeNetworkModel
import com.example.pepah.rxapp.extensions.ld
import com.example.pepah.rxapp.model.MainViewState
import com.example.pepah.rxapp.view.MainView
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers


/**
 * Created by pepa on 13/05/2017.
 */

class MainPresenter : MviBasePresenter<MainView, MainViewState>() {

    override fun bindIntents() {

        val login: Observable<MainViewState> = intent(MainView::loginIntent).doOnNext {
            ld("Logging username ${it.first} password: ${it.second}")}.map {
                var state: MainViewState? = null
                if (AwesomeNetworkModel.checkLogin(it.first, it.second)) {
                    state = MainViewState.LoginResult("Welcome ${it.first}")
                } else {
                    state = MainViewState.WrongLogin("Invalid Credentials")
                }
                state!!
            }
            .startWith { MainViewState.Loading()  }
                .observeOn(AndroidSchedulers.mainThread())


        subscribeViewState(login, MainView::render)



//        var allIntents = Observable.merge(login, passwordIntent())
    }


}