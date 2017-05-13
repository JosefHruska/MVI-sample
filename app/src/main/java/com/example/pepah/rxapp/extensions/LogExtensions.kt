package com.example.pepah.rxapp.extensions

import android.os.Looper
import android.util.Log

/**
 * TODO: Add description
 *
 * @author Josef Hruška (josef@stepuplabs.io)
 */
fun ld(logText: String) {
    Log.d("settle-up", logText)
}

fun ld(logText: Any) {
    Log.d("settle-up", logText.toString())
}

fun li(logText: String) {
    Log.i("settle-up", logText)
}

//fun logError(error: Throwable) {
//    error.printStackTrace()
//    FirebaseCrash.log("User ID: ${Auth.getUserId()}")
//    FirebaseCrash.report(error)
//}
//
//fun logErrorVisible(error: Throwable): String {
//    val line = error.stackTrace[0].lineNumber
//    val hash = RandomStringUtils.randomAlphabetic(3).toUpperCase()
//    val errorCode = "$line-$hash"
//    FirebaseCrash.log("Error code: $errorCode")
//    logError(error)
//    return errorCode
//}
//
//fun logErrorVisible(error: Throwable, groupId: String): String {
//    FirebaseCrash.log("Group ID: $groupId")
//    return logErrorVisible(error)
//}

var time: Long = 0

fun t(logText: String) {
    val currentTime = System.currentTimeMillis()
    ld("$logText: ${currentTime - time} ms since last measurement")
    time = currentTime
}

fun checkThread() {
    ld("MainThread=" + (Looper.myLooper() == Looper.getMainLooper()) + "( " + Thread.currentThread() + ")")
}
