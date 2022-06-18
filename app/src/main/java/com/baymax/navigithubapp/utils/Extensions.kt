package com.baymax.navigithubapp.utils

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun String?.toTimeAgo(): String? {
    if(this==null) return this
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    sdf.timeZone = TimeZone.getTimeZone("GMT")
    try {
        val time: Long = sdf.parse("2016-01-24T16:00:00.000Z")?.time ?: return null
        val now = System.currentTimeMillis()
        return DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS).toString()
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return this
}