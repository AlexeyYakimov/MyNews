package ru.polosatuk.mycard;

import android.app.Application;
import android.content.Context;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class App extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
    AndroidThreeTen.init(this);
        appContext = this;
    }

    public static Context getAppContext(){
        return appContext;
    }
}
