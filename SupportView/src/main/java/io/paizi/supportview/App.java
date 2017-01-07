package io.paizi.supportview;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by pai on 2016/12/14.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

}
