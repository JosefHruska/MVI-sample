package com.example.pepah.rxapp.ui

import android.view.View
import com.example.pepah.rxapp.R
import com.example.pepah.rxapp.extensions.disable
import com.example.pepah.rxapp.extensions.enable
import com.example.pepah.rxapp.model.MainViewState
import com.example.pepah.rxapp.presenter.MainPresenter
import com.example.pepah.rxapp.view.MainView
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : MviActivity<MainView, MainPresenter>(), MainView {

//    override fun getLayoutRes(): Int {
//        return R.layout.activity_main
//    }

    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun userNameIntent(): Observable<String> {
        return RxTextView.textChanges(vName) // Thanks Jake Wharton :)
                .filter({ queryString -> queryString.contains(".com") || queryString.isEmpty() })
                .debounce(500, TimeUnit.MILLISECONDS).map { it.toString() }
    }

    override fun passwordIntent(): Observable<String> {
        return RxTextView.textChanges(vPassword) // Thanks Jake Wharton :)
                .filter({ queryString -> queryString.length >= 8 || queryString.isEmpty() })
                .debounce(500, TimeUnit.MILLISECONDS).map { it.toString() }
    }

    override fun loginIntent(): Observable<Pair<String, String>> {
        setContentView(R.layout.activity_main)
        return RxView.clicks(vAction).map { Pair(vName.text.toString(), vPassword.text.toString()) }
    }

    override fun render(viewState: MainViewState) {
        if (viewState is MainViewState.LoginNotStarted) {
            renderLoginNotStarted()
        } else if (viewState is MainViewState.Loading) {
            renderLoading()
        } else if (viewState is MainViewState.LoginResult) {
            renderValidLogin((viewState as MainViewState.LoginResult).messageSucces)
        } else if (viewState is MainViewState.WrongLogin) {
            renderWrongLogin()
        } else if (viewState is MainViewState.Error) {
            renderError()
        } else {
            throw IllegalArgumentException("Don't know how to render viewState " + viewState)
        }
    }

    private fun renderValidLogin(messageSucces: String) {
        vProgressBar.setVisibility(View.GONE)
        vAction.disable()
        vCurrentStatus.text = messageSucces
    }

    private fun renderLoginNotStarted() {
        vProgressBar.setVisibility(View.GONE)
        vAction.enable()
        vCurrentStatus.text = "Please Log In"

    }

    private fun renderLoading() {
        vProgressBar.setVisibility(View.VISIBLE)
        vAction.disable()
        vCurrentStatus.text = "Loading"

    }

    private fun renderError() {
        vProgressBar.setVisibility(View.GONE)
        vAction.disable()
        vCurrentStatus.text = "Error"

    }

    private fun renderWrongLogin() {
        vProgressBar.setVisibility(View.GONE)
        vAction.enable()
        vCurrentStatus.text = "Wrong credentials"

    }
}
