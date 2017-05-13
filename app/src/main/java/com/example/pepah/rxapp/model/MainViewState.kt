package com.example.pepah.rxapp.model

/**
 * TODO: Add description
 *
 * @author Josef Hruška (josef@stepuplabs.io)
 */
sealed class MainViewState {

    /**
     * The search has not been stared yet
     */
    class LoginNotStarted : MainViewState()

    /**
     * Loading: Currently waiting for search result
     */
    class Loading : MainViewState()

    /**
     * Indicates that the search has delivered an empty result set
     */
    class WrongLogin(val searchQueryText: String) : MainViewState()

    /**
     * A valid search result. Contains a list of items that have matched the searching criteria.
     */
    class LoginResult(val messageSucces: String) : MainViewState()

    /**
     * Indicates that an error has occurred while searching
     */
    class Error(val error: Throwable) : MainViewState() {
        fun new() {

        }
    }
}