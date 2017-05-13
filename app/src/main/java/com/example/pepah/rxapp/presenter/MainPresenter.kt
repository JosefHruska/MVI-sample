package com.example.pepah.rxapp.presenter

import com.example.pepah.rxapp.R.id.vName
import com.example.pepah.rxapp.R.id.vPassword
import com.example.pepah.rxapp.view.MainView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

/**
 * Created by pepa on 13/05/2017.
 */

class MainPresenter: BasePresenter<MainView> {

    override fun bindIntents() {



        var allIntents = Observable.merge(userNameIntent(), passwordIntent())
//    subscribeViewState(allI)
    }

    override fun onViewAttached(view: MainView) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onViewDetached() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}