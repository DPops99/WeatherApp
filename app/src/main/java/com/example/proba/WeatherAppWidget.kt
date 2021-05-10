package com.example.proba

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.lifecycle.ViewModelProvider
import com.example.proba.main.MainActivity
import com.example.proba.room.viewmodel.RoomFactory
import com.example.proba.room.viewmodel.RoomViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Implementation of App Widget functionality.
 */
class WeatherAppWidget : AppWidgetProvider() {


    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }



    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created

    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

private fun getPendingIntent(context: Context, value: Int): PendingIntent {

    val intent = Intent(context, MainActivity::class.java)
    return PendingIntent.getActivity(context,value, intent, 0)
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.weather_app_widget)
//    views.setTextViewText(R.id.appwidget_text, widgetText)
    views.setOnClickPendingIntent(R.id.app_widget, getPendingIntent(context, 0))
    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}