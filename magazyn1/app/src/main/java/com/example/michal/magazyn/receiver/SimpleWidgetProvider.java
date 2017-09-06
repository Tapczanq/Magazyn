package com.example.michal.magazyn.receiver;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.michal.magazyn.R;



public class SimpleWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            Intent intent = new Intent(context, SimpleService.class);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.simple_appwidget);
            views.setRemoteAdapter(R.id.list, intent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
