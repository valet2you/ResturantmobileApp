package com.viralops.touchlessfoodordering.ui.Support;

import android.app.Application;


public class CustomFontApp extends Application {
    private static CustomFontApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        FontsOverride.setDefaultFont(this, "DEFAULT", "font/Roboto-Light.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "font/Roboto-Light.ttf");
        FontsOverride.setDefaultFont(this, "serif", "font/verdana.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "font/Roboto-Regular.ttf");
      //  FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/raleway.ttf");
    }
    public static synchronized CustomFontApp getInstance() {
        return mInstance;
    }


}
