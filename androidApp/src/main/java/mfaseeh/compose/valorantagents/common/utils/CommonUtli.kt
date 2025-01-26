package mfaseeh.compose.valorantagents.common.utils

import android.content.Context
import android.view.WindowManager

fun getScreenHeight(context: Context): Int {
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val displayMetrics = windowManager.currentWindowMetrics.bounds

    return displayMetrics.height()
}