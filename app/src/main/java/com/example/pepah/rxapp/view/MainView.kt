package com.example.pepah.rxapp.view

import com.example.pepah.rxapp.model.MainViewState
import io.reactivex.Observable

/**
 * Created by pepa on 13/05/2017.
 */

interface MainView: BaseView {

    /**
     * The user name intent

     * @return An observable emitting user name text
     */
    fun userNameIntent(): Observable<String>

    /**
     * The password intent

     * @return An observable emitting password text
     */
    fun passwordIntent(): Observable<String>

    /**
     * Renders the viewState

     * @param viewState The current viewState state that should be displayed
     */
    fun render(viewState: MainViewState)

}