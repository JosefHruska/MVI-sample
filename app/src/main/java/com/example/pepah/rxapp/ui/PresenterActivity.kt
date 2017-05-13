package com.example.pepah.rxapp.ui

import com.example.pepah.rxapp.presenter.BasePresenter
import com.example.pepah.rxapp.view.BaseView

/**
 * Created by pepa on 13/05/2017.
 */

abstract class PresenterActivity<P : BasePresenter<V>, V : BaseView> : BaseActivity() {

    private var mPresenter: P? = null

    abstract fun createPresenter(): P

}