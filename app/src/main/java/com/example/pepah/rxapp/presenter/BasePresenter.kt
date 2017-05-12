package com.example.pepah.rxapp.presenter

import com.example.pepah.rxapp.view.BaseView

/**
 * Created by pepa on 13/05/2017.
 */


    interface BasePresenter<V : BaseView> {

        /**
         * Called when this Presenter was created (new instance) by Loader.
         */
        fun onCreatedByLoader()

        /**
         * Called before this Presenter is destroyed by Loader.
         */
        fun onDestroyedByLoader()

        /**
         * Called when this Presenter is attached to @{MvpView}
         * @param view The @{MvpView} that this Presenter was attached to
         */
        fun onViewAttached(view: V)

        /**
         * Called when this Presenter is detached from @{MvpView}
         */
        fun onViewDetached()
    }
