package com.leosssdroid.tastycocktails;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by Leosss on 19/01/2017.
 */

public class TastyCocktailsApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        TypefaceProvider.registerDefaultIconSets();
    }
}
