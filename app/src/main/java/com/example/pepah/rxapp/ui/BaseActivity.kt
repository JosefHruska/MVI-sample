package com.example.pepah.rxapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by pepa on 13/05/2017.
 */

abstract class BaseActivity: AppCompatActivity() {


    abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        initUi()
    }

    open fun initUi() {
    }

}