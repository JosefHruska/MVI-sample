package com.example.pepah.rxapp.presenter

import com.example.pepah.rxapp.data.AwesomeNetworkModel
import com.example.pepah.rxapp.extensions.ld
import com.example.pepah.rxapp.model.MainViewState
import com.example.pepah.rxapp.view.MainView
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable


/**
 * Created by pepa on 13/05/2017.
 */

class MainPresenter : MviBasePresenter<MainView, MainViewState>() {

    override fun bindIntents() {

        val userName: Observable<MainViewState> = intent(MainView::userNameIntent)
                .doOnNext { ld("Type $it") }.map {
            var state: MainViewState = MainViewState.LoginNotStarted()
            if (!it.endsWith("@gmail.com")) {
                state = MainViewState.WrongUserName("Haha")
            }
            state
        }



        val login: Observable<MainViewState> = intent(MainView::loginIntent).doOnNext {
            ld("Logging username ${it.first} password: ${it.second}")
        }.map {
            var state: MainViewState = MainViewState.LoginNotStarted()
            AwesomeNetworkModel.checkLogin(it.first, it.second).map { isValid ->
                if (isValid) {
                    state = MainViewState.LoginResult("Welcome ${it.first}")
                } else {
                    state = MainViewState.WrongLogin("Invalid Credentials")
                }
            }.subscribe()
            state


        }
        subscribeViewState(login, MainView::render)
    }
}