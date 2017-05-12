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
    class MainNotStartedYet : MainViewState()

    /**
     * Loading: Currently waiting for search result
     */
    class Loading : MainViewState()

    /**
     * Indicates that the search has delivered an empty result set
     */
    class EmptyResult(val searchQueryText: String) : MainViewState()

    /**
     * A valid search result. Contains a list of items that have matched the searching criteria.
     */
    class MainResult(val searchQueryText: String, val result: List<Int>) : MainViewState()

    /**
     * Indicates that an error has occurred while searching
     */
    class Error(val searchQueryText: String, val error: Throwable) : MainViewState()
}