package com.example.pepah.rxapp.extensions

import android.widget.Button
import com.example.pepah.rxapp.R

/**
 * TODO: Add description
 *
 * @author Josef Hruška (josef@stepuplabs.io)
 */


fun Button.disable() {
    this.setBackgroundColor(this.resources.getColor(R.color.gray_1))
}

fun Button.enable() {
    this.setBackgroundColor(this.resources.getColor(R.color.green_positive))
}