package com.example.pepah.rxapp.ui

import android.view.View
import android.widget.Toast
import com.example.pepah.rxapp.R
import com.example.pepah.rxapp.model.MainViewState
import com.example.pepah.rxapp.view.MainView
import com.jakewharton.rxbinding2.support.design.widget.RxTextInputLayout
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.RxTextView

import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit



class MainActivity : BaseActivity(), MainView {

    fun searchIntent(): Observable<String> {
        return RxTextView.textChanges(vName) // Thanks Jake Wharton :)
                .filter({ queryString -> queryString.length > 3 || queryString.isEmpty()})
                .debounce(500, TimeUnit.MILLISECONDS).map { it.toString() }
    }

    fun render(viewState: MainViewState) {
        if (viewState is MainViewState.MainNotStartedYet) {
            renderSearchNotStarted()
        } else if (viewState is MainViewState.Loading) {
            renderLoading()
        } else if (viewState is MainViewState.MainResult) {
            renderResult((viewState as MainViewState.MainResult).getResult())
        } else if (viewState is MainViewState.EmptyResult) {
            renderEmptyResult()
        } else if (viewState is MainViewState.Error) {
            renderError()
        } else {
            throw IllegalArgumentException("Don't know how to render viewState " + viewState)
        }
    }

    private fun renderResult(result: List<Product>) {
        TransitionManager.beginDelayedTransition(container)
        recyclerView.setVisibility(View.VISIBLE)
        loadingView.setVisibility(View.GONE)
        emptyView.setVisibility(View.GONE)
        errorView.setVisibility(View.GONE)
        adapter.setProducts(result)
        adapter.notifyDataSetChanged()
    }

    private fun renderSearchNotStarted() {
        TransitionManager.beginDelayedTransition(container)
        recyclerView.setVisibility(View.GONE)
        loadingView.setVisibility(View.GONE)
        errorView.setVisibility(View.GONE)
        emptyView.setVisibility(View.GONE)
    }

    private fun renderLoading() {
        TransitionManager.beginDelayedTransition(container)
        recyclerView.setVisibility(View.GONE)
        loadingView.setVisibility(View.VISIBLE)
        errorView.setVisibility(View.GONE)
        emptyView.setVisibility(View.GONE)
    }

    private fun renderError() {
        TransitionManager.beginDelayedTransition(container)
        recyclerView.setVisibility(View.GONE)
        loadingView.setVisibility(View.GONE)
        errorView.setVisibility(View.VISIBLE)
        emptyView.setVisibility(View.GONE)
    }

    private fun renderEmptyResult() {
        TransitionManager.beginDelayedTransition(container)
        recyclerView.setVisibility(View.GONE)
        loadingView.setVisibility(View.GONE)
        errorView.setVisibility(View.GONE)
        emptyView.setVisibility(View.VISIBLE)
    }

}
