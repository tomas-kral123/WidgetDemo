package com.example.widgetdemo

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import kotlin.random.Random

// Class handling widget.
class DemoWidget : AppWidgetProvider() {

    /*
    This is called to update the widget at intervals defined by the updatePeriodMillis attribute in the AppWidgetProviderInfo. (See the table describing additional widget attributes in this document).
    This method is also called when the user adds the widget, so it should perform the essential setup, such as define event handlers for View objects or start a job to load data to be displayed in the widget.

    Note: Updates requested with updatePeriodMillis will not be delivered more than once every 30 minutes.

    updatePeriodMillis does not support values smaller than 30 minutes. However, if you want to disable periodic updates, you can specify 0.

    You can also allow users to adjust the frequency in a configuration—for example, they might want a stock ticker to update every 15 minutes, or maybe only four times a day. In this case, set the updatePeriodMillis to 0 and use WorkManager instead.
     */
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        Log.d("ManualWidget", "onUpdate")
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

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = "Text ${Random.nextInt(100)}"
    Log.d("ManualWidget", "updateAppWidget() widgetText = $widgetText")
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.demo_widget)
    views.setTextViewText(R.id.appwidget_text1, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}